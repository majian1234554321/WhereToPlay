package com.fanc.wheretoplay.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.util.Base64;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;


import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.adapter.ImageAdapter;
import com.fanc.wheretoplay.base.BaseActivity;
import com.fanc.wheretoplay.datamodel.SubmitCommentModel;
import com.fanc.wheretoplay.imagepink.utils.ImageSelectorUtils;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;

import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.view.RatingBar;
import com.fanc.wheretoplay.view.TitleBarView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;


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

    @BindView(R.id.et_content)
    EditText et_content;


    private static final int REQUEST_CODE = 0x00000011;


    private ImageAdapter mAdapter;
    private ArrayList<String> imagesfinal;
    private ArrayList<String> imagesDisplay;
    private Bitmap bitmap;
    private String value;
    private String order_idValue;
    private String store_idValue;
    float ratingValue1 = 10f;
    float ratingValue2 = 10f;
    float ratingValue3 = 10f;
    private String addressValue;
    private String discountValue;
    private double discount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publication_evaluation);
        ButterKnife.bind(this);
        tbv.setTv_title("发表评价");

        order_idValue = getIntent().getStringExtra("order_id");
        store_idValue = getIntent().getStringExtra("store_id");
        addressValue = getIntent().getStringExtra("address");
        discountValue = getIntent().getStringExtra("discount");

        if (discountValue !=null&& discountValue.length()>0) {
            discount = Double.parseDouble(discountValue);
        }else {
            discount =  10;
        }


        if (discount > 0) {
            SpannableString text = new SpannableString(discount + "折");
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount), 0, text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setSpan(new TextAppearanceSpan(mContext, R.style.reserve_dicount_small), text.length() - 1, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            tvMerchantDetailDiscountSum.setText(text, TextView.BufferType.SPANNABLE);
            tvMerchantDetailDiscountSum.setVisibility(View.VISIBLE);
        } else {
            tvMerchantDetailDiscountSum.setVisibility(View.GONE);
        }


        rvImage.setLayoutManager(new GridLayoutManager(this, 3));
        imagesfinal = new ArrayList<>();
        imagesfinal.add("这不是图片");
        mAdapter = new ImageAdapter(this, imagesfinal);
        rvImage.setAdapter(mAdapter);

        ratingbar1.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {
                ratingValue1 = ratingCount;
            }
        });
        ratingbar2.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {
                ratingValue2 = ratingCount;
            }
        });
        ratingbar3.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {
                ratingValue3 = ratingCount;
            }
        });


    }


    @OnClick(R.id.btn_commit)
    public void onViewClicked() {


        showProgress();
        StringBuilder base64image = new StringBuilder();
        if (imagesDisplay != null && imagesDisplay.size() > 1 && imagesDisplay.contains("这不是图片")) {
            imagesDisplay.remove("这不是图片");
        }

        if (imagesDisplay != null && imagesDisplay.size() > 1) {
            for (int i = 0; i < imagesDisplay.size(); i++) {
                bitmap = getBitmap(imagesDisplay.get(i));
                value = Bitmap2StrByBase64(bitmap);
                base64image.append("data:image/png;base64,");
                base64image.append(value);
                if (i+1 != imagesDisplay.size()) {
                    base64image.append("|");
                }
            }
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("store_id", store_idValue);
            jsonObject.put("token", new SPUtils(mContext).getUser().getToken());
            jsonObject.put("order_id", order_idValue);
            jsonObject.put("content", et_content.getText().toString().trim());
            jsonObject.put("images", base64image.toString());
            jsonObject.put("environment", ratingValue1+"");
            jsonObject.put("atmosphere", ratingValue2+"");
            jsonObject.put("service", ratingValue3+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }



        MultipartBody.Part requestFileA =
                MultipartBody.Part.createFormData("data", jsonObject.toString() + "");

         Retrofit_RequestUtils.getRequest()
                .SubmitCommentModel(requestFileA)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SubmitCommentModel>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        closeProgress();
                        Toast.makeText(mContext, "提交评论失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(SubmitCommentModel submitCommentModel) {
                        closeProgress();
                        if ("0".equals(submitCommentModel.code)) {
                            Toast.makeText(mContext, "提交评论成功", Toast.LENGTH_SHORT).show();
                            //RxBus.getDefault().post("提交评价成功");
                            startActivity(new Intent(PublicationEvaluationActivity.this,EvaluationSuccessActivity.class));
                            finish();
                        } else {
                            Toast.makeText(mContext, "提交评论失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    private Bitmap getBitmap(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始是先把newOpts.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts); // 此时返回bitmap为null

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 以800*480分辨率为例
        float hh = 800f;  // 这里设置高度为800f
        float ww = 480f;  // 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int scale = 1;  // be=1表示不缩放
        if (w > h && w > ww) {  // 如果宽度大的话根据宽度固定大小缩放
            scale = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) { // 如果高度高的话根据宽度固定大小缩放
            scale = (int) (newOpts.outHeight / hh);
        }
        if (scale <= 0) scale = 1;
        newOpts.inSampleSize = scale; // 设置缩放比例 // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return bitmap;
    }


    public String Bitmap2StrByBase64(Bitmap bit) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 40, bos);//参数100表示不压缩
        //bit.compress(Bitmap.CompressFormat., 40, bos);
        byte[] bytes = bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            imagesDisplay = data.getStringArrayListExtra(ImageSelectorUtils.SELECT_RESULT);
            if (imagesDisplay.size() < 9) {
                imagesDisplay.add("这不是图片");
            }
            mAdapter.refresh(imagesDisplay);
        }
    }


}

