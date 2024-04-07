package cn.stylefeng.guns.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SuccessResponseData extends ResponseData {

    /**
     * 异常的具体类名称
     */
    private String exceptionClazz;

    /**
     * 异常的提示信息
     */
    private String exceptionTip;

    /**
     * 跟项目有关的具体异常位置
     * <p>
     * 一般是堆栈中第一个出现项目包名的地方
     */
    private String exceptionPlace;

    public SuccessResponseData(){
        super(true, DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, null);
    }
    
    public SuccessResponseData(Object object) {
        super(true, DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, object);
    }

    public SuccessResponseData(Integer code, Object object) {
        super(true, code, DEFAULT_SUCCESS_MESSAGE, object);
    }

    public SuccessResponseData(Integer code, String message, Object object) {
        super(true, code, message, object);
    }

}

