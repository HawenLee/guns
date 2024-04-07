package cn.stylefeng.guns.ai.zhipu.service.impl;

import cn.stylefeng.guns.ai.zhipu.dto.OpenAiZhiPuTalkDTO;
import cn.stylefeng.guns.ai.zhipu.service.OpenAiZhiPuService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 *
 * @author lijiale
 * @date 2024/4/1
 */
@Service
public class OpenAiZhiPuServiceImpl implements OpenAiZhiPuService {
    @Value("${openAi.zhipu.apiKey}")
    private String zhiPuApiKey;

    @Override
    public String talk2OpenAi(OpenAiZhiPuTalkDTO dto) {
//        ClientV4 client = new ClientV4.Builder(zhiPuApiKey)
//                // 传输层默认使用okhttpclient，如果需要修改位其他http client（如apache），可以在这里指定。注意apache不支持sse调用
//                .httpTransport(new ApacheHttpClientTransport())
//                .build();

        return null;
    }


}
