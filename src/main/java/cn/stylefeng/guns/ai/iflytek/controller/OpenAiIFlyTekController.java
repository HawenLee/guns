package cn.stylefeng.guns.ai.iflytek.controller;

import cn.stylefeng.guns.ai.aliyun.dto.OpenAiALiYunTalkDTO;
import cn.stylefeng.guns.ai.iflytek.service.OpenAiIFlyTekService;
import cn.stylefeng.guns.common.ResponseData;
import cn.stylefeng.roses.kernel.scanner.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.PostResource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p></p>
 *
 * @author lijiale
 * @date 2024/4/3
 */
@RestController
@ApiResource(name = "科大讯飞的openAi")
public class OpenAiIFlyTekController {
    private static final String URL = "/open/ai/v1/iflytek";
    @Resource
    private OpenAiIFlyTekService openAiIFlyTekService;

    @PostResource(name = "调用openAi的接口", path = URL + "/talk2OpenAi")
    public ResponseData talk2OpenAi(@RequestBody OpenAiALiYunTalkDTO dto) {
        return ResponseData.success(openAiIFlyTekService.talk2OpenAi(dto));
    }
}
