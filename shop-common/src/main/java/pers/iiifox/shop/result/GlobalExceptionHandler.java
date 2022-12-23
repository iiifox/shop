package pers.iiifox.shop.result;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.iiifox.shop.exception.BizException;

/**
 * @author 田章
 * @description 全局异常处理器
 * @date 2022/12/23
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R Handle(Exception e) {
        if (e instanceof BizException) {
            log.error("业务异常 --> {}", e);
            return R.error((BizException) e);
        } else {
            log.error("系统异常 --> {}", e);
            return R.custorError("xxxxx", "全局异常，未知错误");
        }
    }

}
