package cn.stylefeng.guns.ai.aliyun.serivce.impl;

import cn.stylefeng.guns.ai.aliyun.dto.OpenAiALiYunTalkDTO;
import cn.stylefeng.guns.ai.aliyun.serivce.OpenAiALiYunService;
import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author lijiale
 * @date 2024/4/1
 */
@Service
public class OpenAiALiYunServiceImpl implements OpenAiALiYunService {
    @Value("${openAi.aliyun.apiKey}")
    private String aliyunApiKey;
    private String aliyunModel = "qwen-turbo";
    private String aliyunSystemMsgContent = "You are a helpful assistant.";
    Generation gen = null;
    GenerationParam param = null;

    @PostConstruct
    private void init() {
        gen = new Generation();
        param = GenerationParam.builder()
                .apiKey(aliyunApiKey)
                .model(aliyunModel)
//                .messages(messages)
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                .topP(0.8)
                .build();
    }


    @Override
    public String talk2OpenAi(OpenAiALiYunTalkDTO dto) throws NoApiKeyException, InputRequiredException {
        Message systemMsg = Message.builder()
                .role(Role.SYSTEM.getValue())
                .content(aliyunSystemMsgContent)
                .build();
        Message userMsg = Message.builder()
                .role(Role.USER.getValue())
                .content(dto.getPrompt())
                .build();
        List<Message> messages = new ArrayList<>();
        messages.add(systemMsg);
        messages.add(userMsg);
        param.setMessages(messages);
        GenerationResult result = gen.call(param);

        return result.toString();
    }
}
