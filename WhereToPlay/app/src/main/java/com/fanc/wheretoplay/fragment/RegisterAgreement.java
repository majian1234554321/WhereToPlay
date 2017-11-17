package com.fanc.wheretoplay.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.view.TopMenu;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by peace on 2017/11/17.
 */

public class RegisterAgreement extends BaseFragment {

    @BindView(R.id.tm_agreement)
    TopMenu tmAgreement;
//    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_register_agr, container, false);
        ButterKnife.bind(this,view);
        initViews();
        init();
        setListener();
//        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initViews() {
        tmAgreement.setLeftIcon(R.drawable.left);
        tmAgreement.setTitle(R.string.agreement);
        tmAgreement.setTitleColor(getResources().getColor(R.color.white));
    }

    private void init() {

    }

    private void setListener() {
        tmAgreement.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.finish();
            }
        });
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
}
