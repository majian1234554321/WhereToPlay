package com.fanc.wheretoplay.rx;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by DELL on 2017/3/9.
 */

public class RxHelper {
    public static <T> Observable.Transformer<BaseResponseModel<T>, T> handleResult() {
        return new Observable.Transformer<BaseResponseModel<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseResponseModel<T>> tObservable) {
                return tObservable.flatMap(new Func1<BaseResponseModel<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseResponseModel<T> result) {

                        if (result.success()) {
                            return createData(result.content);
                        } else {
                            return Observable.error(new Exception("errcode没有返回——————————>>>>>>>>0<<<<<<<<<"+result.message));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };

    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

    }
}
