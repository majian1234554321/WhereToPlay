package com.fanc.wheretoplay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;

import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.ImageAdapter;
import com.fanc.wheretoplay.base.BaseActivity;
import com.fanc.wheretoplay.imagepink.utils.ImageSelectorUtils;
import com.fanc.wheretoplay.view.TitleBarView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PublicationEvaluationActivity extends BaseActivity {

    @BindView(R.id.tbv)
    TitleBarView tbv;
    @BindView(R.id.tv_merchant_detail_title)
    TextView tvMerchantDetailTitle;
    @BindView(R.id.tv_merchant_reserve_address)
    TextView tvMerchantReserveAddress;
    @BindView(R.id.tv_merchant_detail_discount_sum)
    TextView tvMerchantDetailDiscountSum;
    @BindView(R.id.rv_image)
    RecyclerView rvImage;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.ratingbar1)
    RatingBar ratingbar1;
    @BindView(R.id.ratingbar2)
    RatingBar ratingbar2;
    @BindView(R.id.ratingbar3)
    RatingBar ratingbar3;
    @BindView(R.id.btn_commit)
    Button btnCommit;

    private static final int REQUEST_CODE = 0x00000011;


    private ImageAdapter mAdapter;
    private ArrayList<String> imagesfinal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication_evaluation);
        ButterKnife.bind(this);
        tbv.setTv_title("发表评价");
        rvImage.setLayoutManager(new GridLayoutManager(this, 3));
        imagesfinal = new ArrayList<>();
        imagesfinal.add("这不是图片");
        mAdapter = new ImageAdapter(this,imagesfinal);
        rvImage.setAdapter(mAdapter);

    }




    @OnClick(R.id.btn_commit)
    public void onViewClicked() {
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            ArrayList<String> imagesDisplay = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
            if (imagesDisplay.size()<9){
                imagesDisplay.add("这不是图片");
            }
            mAdapter.refresh(imagesDisplay);
        }
    }


}

