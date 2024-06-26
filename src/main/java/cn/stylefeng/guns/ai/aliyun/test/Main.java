package cn.stylefeng.guns.ai.aliyun.test;

import java.util.*;


import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.JsonUtils;


public class Main {
//    public static void callWithMessage() throws NoApiKeyException, ApiException, InputRequiredException {
//        Generation gen = new Generation();
//        Message systemMsg = Message.builder()
//                .role(Role.SYSTEM.getValue())
//                .content("You are a helpful assistant.")
//                .build();
//        Message userMsg = Message.builder()
//                .role(Role.USER.getValue())
//                .content("你好，周末去哪里玩？")
//                .build();
//        GenerationParam param = GenerationParam.builder()
//                .apiKey("sk-f446659a269f4108a44d7a91d2e857f4")
//                .model("qwen-turbo")
//                .messages(Arrays.asList(systemMsg, userMsg))
//                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
//                .topP(0.8)
//                .build();
//        GenerationResult result = gen.call(param);
//        System.out.println(result);
//    }
//
//
//    public static void main(String[] args) {
//        try {
//            callWithMessage();
//        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
//            System.out.println(e.getMessage());
//        }
//        System.exit(0);
//    }

    public static void callWithMessage() throws NoApiKeyException, ApiException, InputRequiredException {
        Generation gen = new Generation();
        Message systemMsg = Message.builder()
                .role(Role.SYSTEM.getValue())
                .content("You are a helpful assistant.")
                .build();
        Message userMsg = Message.builder()
                .role(Role.USER.getValue())
                .content("如何做西红柿炖牛腩？")
                .build();
        List<Message> messages = new ArrayList<>();
        messages.add(systemMsg);
        messages.add(userMsg);
        GenerationParam param = GenerationParam.builder()
                .apiKey("sk-f446659a269f4108a44d7a91d2e857f4")
                .model("qwen-turbo")
                .messages(messages)
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .topP(0.8)
                .build();
        GenerationResult result = gen.call(param);
        System.out.println(result);
        // 添加assistant返回到messages列表，user/assistant消息必须交替出现
        messages.add(result.getOutput().getChoices().get(0).getMessage());
        // new message
        userMsg = Message.builder()
                .role(Role.USER.getValue())
                .content("不放糖可以吗？")
                .build();
        messages.add(userMsg);
        result = gen.call(param);
        System.out.println(result);
        System.out.println(JsonUtils.toJson(result));
    }


    public static void main(String[] args) {
        try {
            callWithMessage();
        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
            System.out.println(e.getMessage());
        }
        System.exit(0);
    }

}
