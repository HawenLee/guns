package cn.stylefeng.guns.ai.aliyun.controller;

import cn.stylefeng.guns.ai.aliyun.dto.OpenAiALiYunTalkDTO;
import cn.stylefeng.guns.ai.aliyun.serivce.OpenAiALiYunService;
import cn.stylefeng.guns.ai.zhipu.dto.OpenAiZhiPuTalkDTO;
import cn.stylefeng.guns.common.ResponseData;
import cn.stylefeng.roses.kernel.scanner.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.PostResource;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
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
@ApiResource(name = "阿里云OpenAi")
@RestController
public class OpenAiALiYunController {
    private static final String URL = "/open/ai/v1/aliyun";
    @Resource
    private OpenAiALiYunService openAiALiYunService;

    @PostResource(name = "调用openAi的接口", path = URL + "/talk2OpenAi")
    public ResponseData talk2OpenAi(@RequestBody OpenAiALiYunTalkDTO dto) throws NoApiKeyException, InputRequiredException {
        return ResponseData.success(openAiALiYunService.talk2OpenAi(dto));
    }


}
