package com.itheima.exception;

import ch.qos.logback.core.util.StringUtil;
import com.itheima.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        e.printStackTrace();
        //捕获异常，如果异常内有自带的异常信息，则直接输出，没有则返回操作失败
        return Result.error(StringUtils.hasLength(e.getMessage())?e.getMessage():"操作失败");
    }

}
