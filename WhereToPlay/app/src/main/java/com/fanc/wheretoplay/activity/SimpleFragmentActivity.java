package com.fanc.wheretoplay.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.fragment.SimpleFragment;


public class SimpleFragmentActivity extends FragmentActivity {
    SimpleFragment fragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_fragment_layout);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment1,fragment=new SimpleFragment(),"test");
        transaction.commit();
    }
    public void onClick(View v){
        fragment.onClick(v);
    }
}
