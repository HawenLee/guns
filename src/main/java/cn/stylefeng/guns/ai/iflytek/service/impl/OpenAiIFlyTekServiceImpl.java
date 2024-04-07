package cn.stylefeng.guns.ai.iflytek.service.impl;

import cn.stylefeng.guns.ai.aliyun.dto.OpenAiALiYunTalkDTO;
import cn.stylefeng.guns.ai.iflytek.service.OpenAiIFlyTekService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.Payload;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.istack.NotNull;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p></p>
 *
 * @author lijiale
 * @date 2024/4/3
 */
@Service
public class OpenAiIFlyTekServiceImpl extends WebSocketListener implements OpenAiIFlyTekService {
    public final static String authorizationHostUrl = "https://spark-api.xf-yun.com/v3.5/chat";
    //    @Value("${openAi.iflytek.appId}")
    public String APPID = "e515e5f9";
    @Value("${openAi.iflytek.apiKey}")
    public String APIKEY;
    @Value("${openAi.iflytek.apiSecret}")
    public String APISECRET;
    public final static Gson json = new Gson();
    public static String answer = "";
    public static String question = "中国面积有多大";
    public static String questionAnswer = "";
    public static Boolean finishFlag = Boolean.FALSE;


    @Override
    public String talk2OpenAi(OpenAiALiYunTalkDTO dto) {
        try {
            finishFlag = Boolean.FALSE;
            question = dto.getPrompt();
            this.chatSparkDesk(question, getAuthorizationUrl());
            String answer = this.answer;
            long start = System.currentTimeMillis();
            while (StringUtils.isBlank(answer)) {
                answer = this.answer;
                if ((System.currentTimeMillis() - start) > 5000) {
                    break;
                }
            }
            System.out.println(answer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (; ; ) {
            if (finishFlag) {
                break;
            }
        }
        return questionAnswer;
    }


    public void chatSparkDesk(String question, String url) {
        this.question = question;
        this.answer = "";
        this.questionAnswer = "";
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newWebSocket(request, new OpenAiIFlyTekServiceImpl());
    }

    @Override
    public void onOpen(WebSocket webSocket, @NotNull Response response) {
        JsonObject frame = new JsonObject();
        JsonObject header = new JsonObject();
        JsonObject chat = new JsonObject();
        JsonObject parameter = new JsonObject();
        JsonObject payload = new JsonObject();
        JsonObject message = new JsonObject();
        JsonObject text = new JsonObject();
        JsonArray ja = new JsonArray();
        header.addProperty("app_id", APPID);
        header.addProperty("uid", UUID.randomUUID().toString().replaceAll("-", ""));
        chat.addProperty("domain", "generalv3.5");
        chat.addProperty("temperature", 0.5);
        chat.addProperty("max_tokens", 1024);
        parameter.add("chat", chat);
        text.addProperty("role", "user");
        text.addProperty("content", question);
        ja.add(text);
        message.add("text", ja);
        payload.add("message", message);
        frame.add("header", header);
        frame.add("parameter", parameter);
        frame.add("payload", payload);
        webSocket.send(frame.toString());
    }


    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        System.out.println(text);
        JSONObject responseData = JSONObject.parseObject(text);
        if (0 == responseData.getJSONObject("header").getInteger("code")) {
            if (2 != responseData.getJSONObject("header").getInteger("status")) {
                JSONObject pl = responseData.getJSONObject("payload");
                JSONArray temp = pl.getJSONObject("choices").getJSONArray("text");
                JSONObject jo = temp.getJSONObject(0);
                questionAnswer += jo.getString("content");
            } else {
                JSONObject pl1 = responseData.getJSONObject("payload");
                JSONArray temp1 = pl1.getJSONObject("choices").getJSONArray("text");
                JSONObject jo = temp1.getJSONObject(0);
                questionAnswer += jo.getString("content");
                answer = questionAnswer;
                webSocket.close(3, "客户端主动断开链接");
                finishFlag = Boolean.TRUE;
            }
        } else {
            System.out.println("返回结果错误：=======code:" + responseData.getJSONObject("header").getInteger("code")
                    + responseData.getJSONObject("header").getString("message"));
            finishFlag = Boolean.TRUE;
            webSocket.close(3, "客户端主动断开链接");
        }
    }

    //鉴权url
    public String getAuthorizationUrl() throws Exception {
        URL url = new URL(authorizationHostUrl);
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = format.format(new Date());
        Charset charset = StandardCharsets.UTF_8;
        Mac mac = Mac.getInstance("hmacsha256");
        SecretKeySpec sp = new SecretKeySpec(APISECRET.getBytes(charset), "hmacsha256");
        mac.init(sp);
        String builder = "host: " + url.getHost() + "\n" +
                "date: " + date + "\n" +
                "GET " + url.getPath() + " HTTP/1.1";
        byte[] basebefore = mac.doFinal(builder.getBytes(charset));
        String signature = Base64.getEncoder().encodeToString(basebefore);
        String authorization_origin = String.format("api_key=\"%s\",  algorithm=\"%s\", headers=\"%s\", signature=\"%s\"", APIKEY, "hmac-sha256", "host date request-line", signature);
        String authorization = Base64.getEncoder().encodeToString(authorization_origin.getBytes(charset));
        HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse("https://" + url.getHost() + url.getPath())).newBuilder().//
                addQueryParameter("authorization", authorization).//
                addQueryParameter("date", date).//
                addQueryParameter("host", url.getHost()).//
                build();
        return httpUrl.toString().replace("https://", "wss://").replace("http://", "ws://");
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        if (response != null && response.code() == 403) {
            answer = "403";
        }
        if (response != null && response.code() == 11200) {
            //发邮件通知地方
//            MailInfo mailInfo = SpringMailUtil.sendPrepare("xxxxxxx", "xxxxxx", "科飞tokens可能不够");
//            SpringMailUtil.sendTxtMail(mailInfo);
            answer = "11200";
        }
    }
}
