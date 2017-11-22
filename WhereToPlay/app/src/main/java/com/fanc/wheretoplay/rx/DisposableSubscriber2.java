package com.fanc.wheretoplay.rx;

import io.reactivex.subscribers.DisposableSubscriber;

import static retrofit2.Response.success;

/**
 * Created by admin on 2017/11/21.
 */

public abstract class DisposableSubscriber2<T> extends DisposableSubscriber<BaseResponseModel<T>> {


    protected abstract void successful(T content);

    public abstract void failed(String t);

    @Override
    public void onNext(BaseResponseModel<T> tBaseResponseModel) {
        if ("0".equals(tBaseResponseModel.code)) {
            successful(tBaseResponseModel.content);
        } else {
            failed(tBaseResponseModel.message);
        }

    }

    @Override
    public void onError(Throwable throwable) {
        failed(throwable.toString());
    }

    @Override
    public void onComplete() {

    }
}
