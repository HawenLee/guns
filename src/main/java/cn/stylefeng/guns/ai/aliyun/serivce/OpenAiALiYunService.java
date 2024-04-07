package cn.stylefeng.guns.ai.aliyun.serivce;

import cn.stylefeng.guns.ai.aliyun.dto.OpenAiALiYunTalkDTO;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;

/**
 * <p></p>
 *
 * @author lijiale
 * @date 2024/4/1
 */
public interface OpenAiALiYunService {
    String talk2OpenAi(OpenAiALiYunTalkDTO dto) throws NoApiKeyException, InputRequiredException;
}
