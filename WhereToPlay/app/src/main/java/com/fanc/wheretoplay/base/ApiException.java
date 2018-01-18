package com.fanc.wheretoplay.base;

/**
 * @author admin
 * @date 2018/1/17
 */

public class ApiException extends   RuntimeException {


    public ApiException(String detailMessage) {
        super(detailMessage);
    }
}
