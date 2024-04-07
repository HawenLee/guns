package cn.stylefeng.guns.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ErrorResponseData extends ResponseData {

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

    public ErrorResponseData(){
        super(false,DEFAULT_ERROR_CODE,DEFAULT_ERROR_MESSAGE,null);
    }
    
    public ErrorResponseData( String message) {
        super(false, DEFAULT_ERROR_CODE, message, null);
    }

    public ErrorResponseData(Integer code, String message) {
        super(false, code, message, null);
    }

    public ErrorResponseData(Integer code, String message, Object object) {
        super(false, code, message, object);
    }

}

