package com.fanc.wheretoplay.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseFragmentActivity;
import com.fanc.wheretoplay.databinding.ActivityCheckCommentBinding;
import com.fanc.wheretoplay.databinding.ActivityCheckCommentsBinding;

/**
 * Created by peace on 2017/10/30.
 */

public class CheckCommentsActivity extends BaseFragmentActivity {

    private ActivityCheckCommentsBinding mCheckCommentsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCheckCommentsBinding = DataBindingUtil.setContentView(this, R.layout.activity_check_comments);
    }
}
