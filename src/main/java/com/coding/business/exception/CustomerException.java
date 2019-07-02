package com.coding.business.exception;

/**
 * 自定义用户异常
 */
public class CustomerException extends Exception {

    public CustomerException(String errMsg){
        super(errMsg);
    }
}
