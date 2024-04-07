package cn.stylefeng.guns.ai.zhipu.service;

import cn.stylefeng.guns.ai.zhipu.dto.OpenAiZhiPuTalkDTO;

/**
 * <p></p>
 *
 * @author lijiale
 * @date 2024/4/1
 */
public interface OpenAiZhiPuService {
    /**
     * 对话 智谱AI
     *
     * @param dto
     * @return
     */
    String talk2OpenAi(OpenAiZhiPuTalkDTO dto);
}
