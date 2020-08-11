package com.devep.exception;

/**
 * @author: ransi
 * @description: 数据不齐异常
 * @create: 2020/01/09
 */
public class BusinessException extends Exception{
    public BusinessException() {
        super();
    }
    public BusinessException(String message){
        super(message);
    }
}
