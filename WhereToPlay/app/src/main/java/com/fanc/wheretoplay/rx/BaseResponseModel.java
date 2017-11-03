package com.fanc.wheretoplay.rx;

import java.io.Serializable;

/**
 * Created by DELL on 2017/3/9.
 */

public class BaseResponseModel<T> implements Serializable {
    public String code;
    public String message;
    public T content;


    public boolean success() {
        return code.equals("0");
    }


    @Override
    public String toString() {
        return "BaseModel{" +
                "code='" + code + '\'' +
                ", msg='" + message + '\'' +
                ", data=" + content +
                '}';
    }

}
