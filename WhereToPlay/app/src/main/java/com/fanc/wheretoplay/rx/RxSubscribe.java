package com.fanc.wheretoplay.rx;

import rx.Subscriber;

/**
 * Created by DELL on 2017/3/9.
 */

public abstract class RxSubscribe<T> extends Subscriber<T> {
    @Override
    public void onNext(T t) {
        _onNext(t);
    }
    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
//        if (TDevice.getNetworkType() == 0) {
//            _onError("网络不可用");
//        } else

            if (e instanceof Exception) {
            _onError(e.getMessage());
        } else {
            _onError("请求失败，请稍后再试...");
        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

    @Override
    public void onCompleted() {

    }
}