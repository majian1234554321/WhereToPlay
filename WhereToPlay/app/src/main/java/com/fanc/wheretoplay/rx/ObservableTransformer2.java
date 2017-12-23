package com.fanc.wheretoplay.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author admin
 * @date 2017/12/14
 */

public class ObservableTransformer2<T> implements ObservableTransformer<T,T> {
    @Override
    public ObservableSource apply(Observable observable) {
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
