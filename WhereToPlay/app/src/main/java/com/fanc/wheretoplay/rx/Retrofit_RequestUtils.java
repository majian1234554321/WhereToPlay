package com.fanc.wheretoplay.rx;





import com.fanc.wheretoplay.network.Network;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by admin on 2016/3/18.
 */
public class Retrofit_RequestUtils {
    private volatile static Network.LH_API request;

    private Retrofit_RequestUtils(){}

    public static Network.LH_API getRequest() {
        if (request == null) {
//            File   cacheFile = new File(BaseApplication.getContext().getExternalCacheDir(), "ssqsCache");
//            Cache  cache = new Cache(cacheFile, 1024 * 1024 * 50);
            synchronized (Retrofit_RequestUtils.class){
                if (request==null){
                    OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                    .cache(cache)
                            .connectTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20,TimeUnit.SECONDS)
                            .build();
                    request = new Retrofit.Builder()
                            .baseUrl(Network.BASE + Network.API)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(okHttpClient)
                            .build().create(Network.LH_API.class);
                }
            }


        }

        return request;
    }



}
