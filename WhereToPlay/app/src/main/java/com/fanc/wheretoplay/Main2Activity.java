package com.fanc.wheretoplay;

import android.os.Bundle;

import com.fanc.wheretoplay.datamodel.BookListModel;
import com.fanc.wheretoplay.datamodel.CouponListModel;
import com.fanc.wheretoplay.rx.DisposableSubscriber2;
import com.fanc.wheretoplay.rx.FlowableTransformer2;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.sdu.didi.openapi.DiDiWebActivity;

import java.util.LinkedList;

import okhttp3.MultipartBody;

public class Main2Activity extends DiDiWebActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
