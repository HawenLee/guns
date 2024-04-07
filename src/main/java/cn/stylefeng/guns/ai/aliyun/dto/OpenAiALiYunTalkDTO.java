package cn.stylefeng.guns.ai.aliyun.dto;

import cn.stylefeng.roses.kernel.rule.annotation.ChineseDescription;
import cn.stylefeng.roses.kernel.rule.pojo.request.BaseRequest;
import lombok.Data;

/**
 * <p></p>
 *
 * @author lijiale
 * @date 2024/4/1
 */
@Data
public class OpenAiALiYunTalkDTO extends BaseRequest {
    @ChineseDescription("用户输入的文本内容")
    private String prompt;
}
