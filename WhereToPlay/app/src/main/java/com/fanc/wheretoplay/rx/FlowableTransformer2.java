package com.fanc.wheretoplay.rx;

import com.fanc.wheretoplay.datamodel.CouponListModel;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2017/11/22.
 */

public class FlowableTransformer2<T> implements FlowableTransformer<T,T> {
    @Override
    public Publisher<T> apply(Flowable<T> flowable) {
        return flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

}
