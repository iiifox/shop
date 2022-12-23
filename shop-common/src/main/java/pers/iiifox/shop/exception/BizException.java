package pers.iiifox.shop.exception;

import lombok.Getter;
import pers.iiifox.shop.result.ErrorCodeEnum;

/**
 * @author 田章
 * @description 业务异常类
 * @date 2022/12/23
 */
@Getter
public class BizException extends RuntimeException {

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;

    public BizException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
        this.code = errorCodeEnum.getCode();
        this.message = errorCodeEnum.getMessage();
    }

}