package org.codenotknock.springboot.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiaofu
 * @ControllerAdvice 注解  定义全局异常
 */

@ControllerAdvice
public class MyHandlerException {

    @ExceptionHandler({RuntimeException.class})
    @ResponseBody
    public String handlerException(Exception ex) {
        // 如果出现了相关的异常，就会调用该方法
        // 获取异常信息，返回如 String 的结果
        String message = ex.getMessage();

        return message;
    }
}
