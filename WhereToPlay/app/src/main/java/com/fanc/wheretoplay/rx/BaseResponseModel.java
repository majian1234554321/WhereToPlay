package com.fanc.wheretoplay.rx;





/**
 * Created by DELL on 2017/3/9.
 */

public class BaseResponseModel<T>  {
    public String code;
    public String message;
    public T content;


    public boolean success() {
        return "0".equals(code);
    }


    @Override
    public String toString() {
        return "BaseResponseModel{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }

}
