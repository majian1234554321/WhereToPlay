package com.fanc.wheretoplay.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author admin
 * @date 2017/12/18
 */

public abstract class Observer2<T extends BaseResponseModel> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable disposable) {

    }

    @Override
    public void onNext(T t) {
        if ("0".equals(t.code)) {
            successful(t);
        } else {
            failed(t.message);
        }
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }

    abstract void successful(T content);

    abstract void failed(String t);


}
