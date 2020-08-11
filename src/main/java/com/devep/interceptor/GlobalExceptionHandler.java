package com.devep.interceptor;

import com.devep.base.Result;
import com.devep.common.Results;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: ransi
 * @description: 控制器异常处理类
 * @create: 2019/10/15
 */
@ControllerAdvice
public class GlobalExceptionHandler
{
    /**
     * 参数异常处理
     * @param bException
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public Result bindingResultErrorHandler(BindException bException)
    {
        return Results.failureWithStatus(1, bException.getFieldError().getDefaultMessage());
    }
}
