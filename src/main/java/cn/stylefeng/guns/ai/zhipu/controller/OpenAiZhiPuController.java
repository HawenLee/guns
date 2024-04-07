package cn.stylefeng.guns.ai.zhipu.controller;

import cn.stylefeng.guns.ai.zhipu.dto.OpenAiZhiPuTalkDTO;
import cn.stylefeng.guns.ai.zhipu.service.OpenAiZhiPuService;
import cn.stylefeng.guns.common.ResponseData;
import cn.stylefeng.roses.kernel.scanner.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.PostResource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p></p>
 *
 * @author lijiale
 * @date 2024/4/1
 */
@ApiResource(name = "智谱AI调用")
@RestController
public class OpenAiZhiPuController {
    private static final String URL = "/open/ai/v1/zhipu";
    @Resource
    private OpenAiZhiPuService openAiZhiPuService;

    @PostResource(name = "调用openAi的接口", path = URL + "/talk2OpenAi")
    public ResponseData talk2OpenAi(@RequestBody OpenAiZhiPuTalkDTO dto) {
        return ResponseData.success(openAiZhiPuService.talk2OpenAi(dto));
    }
}

