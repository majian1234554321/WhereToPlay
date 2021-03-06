package com.fanc.wheretoplay.fragment;

import android.Manifest;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.base.BaseFragment;
import com.fanc.wheretoplay.databinding.FragmentMineInfoBinding;
import com.fanc.wheretoplay.datamodel.CityResource;
import com.fanc.wheretoplay.datamodel.DelectCollection;
import com.fanc.wheretoplay.datamodel.IsOk;
import com.fanc.wheretoplay.datamodel.MineMoney;
import com.fanc.wheretoplay.datamodel.Url;
import com.fanc.wheretoplay.image.GlideGalleryImageLoader;
import com.fanc.wheretoplay.network.Network;
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils;
import com.fanc.wheretoplay.rx.RxBus;
import com.fanc.wheretoplay.util.Constants;
import com.fanc.wheretoplay.util.DateFormatUtil;
import com.fanc.wheretoplay.util.FileUtils;
import com.fanc.wheretoplay.util.SPUtils;
import com.fanc.wheretoplay.util.ToastUtils;
import com.fanc.wheretoplay.util.UIUtils;
import com.fanc.wheretoplay.view.CircleImageView;
import com.fanc.wheretoplay.view.ModifyGenderDialog;
import com.fanc.wheretoplay.view.ModifyHeadDialog;
import com.fanc.wheretoplay.view.PickerDialog;
import com.fanc.wheretoplay.view.TopMenu;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.DCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.MultipartBody;

import static android.app.Activity.RESULT_OK;


/**
 * Created by Administrator on 2017/6/15.
 */

public class MineInfoFragment extends BaseFragment {

    private final String GENDER = "0";// 性别默认
    private final String GENDER_MALE = "1";// 男
    private final String GENDER_FEMALE = "2";// 女

    FragmentMineInfoBinding mineInfoBinding;

    TopMenu mTmMineInfo;
    CircleImageView mCivMineInfo;
    EditText mEtMineInfoNickname;
    TextView mTvMineInfoGender;
    TextView mTvMineInfoBirthday;
    TextView mTvMineInfoCity;
    EditText mEtMineInfoMobile;
    EditText mEtMineInfoSign;

    List<CityResource.Province> provinces;
    List<CityResource.City> cities;
    Handler handler;
    // 选中城市的id
    String cityId;
    private MultipartBody.Part requestFileG;
    private MultipartBody.Part requestFileA;
    private MultipartBody.Part requestFileB;
    private MultipartBody.Part requestFileC;
    private MultipartBody.Part requestFileD;
    private MultipartBody.Part requestFileE;
    private MultipartBody.Part requestFileF;
    private Uri contentUri;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mineInfoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine_info, null, false);
        initGallery();
        initViews();
        init();
        setListeners();
        return mineInfoBinding.getRoot();
    }

    /**
     * 初始化Gallery
     */
    private void initGallery() {
        //设置主题
        ThemeConfig themeConfig = ThemeConfig.DEFAULT;
        //配置功能
        FunctionConfig.Builder functionConfigBuilder = new FunctionConfig.Builder();
        functionConfigBuilder
                .setEnableCamera(true)// 使用相机
                .setEnableEdit(true)// 编辑
                .setEnableCrop(true)// 裁剪
                .setCropHeight(320) // 裁剪高度
                .setForceCrop(true)
                .setCropWidth(320)// 裁剪宽度
                .setCropReplaceSource(true)
                .setCropSquare(true) // 方形裁剪
                .setEnablePreview(true)
                .setRotateReplaceSource(true)
                .setEnableRotate(true);
        FunctionConfig functionConfig = functionConfigBuilder.build();
        ImageLoader imageLoader = new GlideGalleryImageLoader();
        File editCacheFile = new File(FileUtils.getIconDir() + "/edit");
        File cacheFile = new File(FileUtils.getIconDir());

        Log.d("aaa", "initGallery: editCacheFile =" + FileUtils.getIconDir() + "/edit" + "\t cacheFile = " + FileUtils.getIconDir());
        CoreConfig coreConfig = new CoreConfig.Builder(mContext, imageLoader, themeConfig)
                .setEditPhotoCacheFolder(editCacheFile)
                .setTakePhotoFolder(cacheFile)
                .setFunctionConfig(functionConfig)
                .build();
        GalleryFinal.init(coreConfig);
    }

    private void initViews() {
        mTmMineInfo = mineInfoBinding.tmMineInfo;
        mCivMineInfo = mineInfoBinding.civMineInfoPhoto;
        mEtMineInfoNickname = mineInfoBinding.etMineInfoNickname;
        mTvMineInfoGender = mineInfoBinding.tvMineInfoGender;
        mTvMineInfoBirthday = mineInfoBinding.tvMineInforBirthday;
        mTvMineInfoCity = mineInfoBinding.tvMineInfoLocation;
        mEtMineInfoMobile = mineInfoBinding.etMineInfoMobile;
        mEtMineInfoSign = mineInfoBinding.etMineInfoSign;
    }

    private void init() {
        mTmMineInfo.setLeftIcon(R.drawable.left);
        mTmMineInfo.setTitle(R.string.mine_info);
        mTmMineInfo.setTitleColor(getResources().getColor(R.color.white));
        mineInfoBinding.setClick(this);
        loadCityList();
    }

    private void setListeners() {
        mTmMineInfo.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
    }

    private void loadCityList() {
        handler = new InnerHandler();
        new Thread() {
            @Override
            public void run() {
                provinces = getProvinceList();
                cities = getCityList();
                handler.sendEmptyMessage(0);
            }
        }.start();
    }

    /**
     * 省列表
     *
     * @return
     */
    private List<CityResource.Province> getProvinceList() {
        List<CityResource.Province> provinces = null;
        try {
            InputStream inputStream = mContext.getAssets().open("city.txt");
            int length = inputStream.available();
            byte[] cityBytes = new byte[length];
            inputStream.read(cityBytes);
            String json = new String(cityBytes);
//            Logger.json(json);
            Gson gson = new Gson();
            CityResource cityResource = gson.fromJson(json, CityResource.class);
            provinces = cityResource.provinces;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return provinces;
    }

    /**
     * 获取城市列表
     *
     * @return
     */
    private List<CityResource.City> getCityList() {
        List<CityResource.City> cities = new ArrayList<>();
        for (int i = 0; i < provinces.size(); i++) {
            cities.addAll(provinces.get(i).getChild());
        }
        return cities;
    }


    class InnerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            initMineInfo();
        }
    }

    private void initMineInfo() {
        Glide.with(mContext).load(Network.IMAGE + mUser.getAvatar()).into(mCivMineInfo);
        //昵称
        if (TextUtils.isEmpty(mUser.getNickname())) {
            mEtMineInfoNickname.setText(R.string.nickname);
        } else {
            mEtMineInfoNickname.setText(mUser.getNickname());
        }
        // 性别
        if (TextUtils.isEmpty(mUser.getGender()) || GENDER.equals(mUser.getGender())) {
            mTvMineInfoGender.setText(R.string.please_choose);
        } else {
            mTvMineInfoGender.setText(GENDER_MALE.equals(mUser.getGender())
                    ? UIUtils.getString(R.string.gender_male) : UIUtils.getString(R.string.gender_female));
        }
        //生日
        if (TextUtils.isEmpty(mUser.getBirthday()) || TextUtils.equals("0", mUser.getBirthday())) {
            mTvMineInfoBirthday.setText(R.string.please_choose);
        } else {
            mTvMineInfoBirthday.setText(DateFormatUtil.getDateStr(new Date(Long.parseLong(mUser.getBirthday()) * 1000)));
        }
        // 所在地
        if (TextUtils.isEmpty(mUser.getRegistered())) {
            mTvMineInfoCity.setText(R.string.please_choose);
        } else {
            CityResource.City city = null;
            for (CityResource.City city1 : cities) {
                if (city1 != null && city1.getId().equals(mUser.getRegistered())) {
                    city = city1;
                    break;
                }
            }
            CityResource.Province province = null;
            if (city != null ) {
                for (CityResource.Province province1 : provinces) {
                    if (province1.getId().equals(city.getPid())) {
                        province = province1;
                        break;
                    }
                }
                mTvMineInfoCity.setText(province.getName() + " " + city.getName());
            }
        }
        mEtMineInfoMobile.setText(mUser.getMobile());
        mEtMineInfoSign.setText(mUser.getSignature());
    }

    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ll_mine_info_photo:
                modifyHeader();
                break;
            case R.id.rl_mine_info_gender:
                modifyGender();
                break;
            case R.id.rl_mine_info_birthday:
                modifyBirthday();
                break;
            case R.id.rl_mine_info_location:
                modifyCity();
                break;
            case R.id.btn_mine_info_confirm:
                confirmModify();
                break;
            default:
                break;
        }
    }

    private void modifyCity() {
        new PickerDialog(mContext)
                .setCityContent(provinces)
                .setConfirmClickListener(new PickerDialog.OnBtnClickListener() {
                    @Override
                    public void onBtnClick(View view, String selectedString, int provinceIndex, String city, int cityIndex) {
                        mTvMineInfoCity.setText(selectedString + " " + city);
                        cityId = provinces.get(provinceIndex).getChild().get(cityIndex).getId();
                    }
                })
                .setCancelClickListener()
                .setCanceledOnTouchOutside(true)
                .show();
    }


    /**
     * 修改生日
     */
    private void modifyBirthday() {
        new PickerDialog(mContext)
                .setBirthdayContent()
                .setConfirmClickListener(new PickerDialog.OnBtnClickListener() {
                    @Override
                    public void onBtnClick(View view, String selectedString, int provinceIndex, String city, int cityIndex) {
                        mTvMineInfoBirthday.setText(selectedString);
                    }
                })
                .setCancelClickListener()
                .setCanceledOnTouchOutside(true)
                .show();
    }

    /**
     * 修改性别
     */
    private void modifyGender() {
        String gender = mTvMineInfoGender.getText().toString();
        if (TextUtils.equals(UIUtils.getString(R.string.gender_male), gender)) {
            gender = GENDER_MALE;
        } else if (TextUtils.equals(UIUtils.getString(R.string.gender_female), gender)) {
            gender = GENDER_FEMALE;
        } else {
            gender = null;
        }
        new ModifyGenderDialog(mContext)
                .setSelectedGender(gender)
                .setOnGenderSelectedListener(new ModifyGenderDialog.OnGenderSelectedListener() {
                    @Override
                    public void onGenderSelected(String gender) {
                        mTvMineInfoGender.setText(gender);
                    }
                })
                .setCanceledOnTouchOutside(true)
                .show();
    }


    /**
     * 更改头像
     */
    private void modifyHeader() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] permissions = new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            };
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(mContext, permissions, Constants.REQUEST_PERMISSION_CODE);
                }
            }
        }
        new ModifyHeadDialog(mContext)
                .setTakePhotoOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        selectIcon(1);
                    }
                })
                .setGalleryOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectIcon(2);
                    }
                })
                .setCanceledOnTouchOutside(true)
                .show();
    }

    /**
     * 根据点击事件来判断是拍照还是打开相册
     *
     * @param which 事件的标志
     */
    public void selectIcon(int which) {
        switch (which) {
            case 1: //拍照
               // GalleryFinal.openCamera(1001, mOnHanlderResultCallback);

                //检测是否有相机和读写文件权限
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    mContext.requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 10085);
                } else {
                    startCamera();
                }

                break;
            case 2: //打开相册
                GalleryFinal.openGallerySingle(1002, mOnHanlderResultCallback);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10085:
                // 如果权限被拒绝，grantResults 为空
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startCamera();
                } else {
                    Toast.makeText(mContext, "改功能需要相机和读写文件权限", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }


    private void startCamera() {
        File imagePath = new File(Environment.getExternalStorageDirectory(), "images");
        if (!imagePath.exists()) imagePath.mkdirs();
        File newFile = new File(imagePath, "default_image.jpg");

        //第二参数是在manifest.xml定义 provider的authorities属性
        contentUri = FileProvider.getUriForFile(mContext, "com.fanc.wheretoplay.camera_photos", newFile);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //兼容版本处理，因为 intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION) 只在5.0以上的版本有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ClipData clip =
                    ClipData.newUri(mContext.getContentResolver(), "A photo", contentUri);
            intent.setClipData(clip);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            List<ResolveInfo> resInfoList =
                    mContext.getPackageManager()
                            .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo resolveInfo : resInfoList) {
                String packageName = resolveInfo.activityInfo.packageName;
                mContext.grantUriPermission(packageName, contentUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        startActivityForResult(intent, 1000);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            ContentResolver contentProvider = mContext.getContentResolver();

            ParcelFileDescriptor mInputPFD;
            try {
                //获取contentProvider图片
                mInputPFD = contentProvider.openFileDescriptor(contentUri, "r");

                FileDescriptor fileDescriptor = mInputPFD.getFileDescriptor();

             //   mCivMineInfo.setImageBitmap(BitmapFactory.decodeFileDescriptor(fileDescriptor));

              File headPhoto =   compressImage(BitmapFactory.decodeFileDescriptor(fileDescriptor));

                Glide.with(mContext)
                        .load(headPhoto)
                        .centerCrop()
                        .override(R.dimen.width_45, R.dimen.height_45)
                        .into(mCivMineInfo);
                //上传图片到服务器
                modifyAvatar(headPhoto);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public static File compressImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 500) {  //循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            long length = baos.toByteArray().length;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(System.currentTimeMillis());
        String filename = format.format(date);
        File file = new File(Environment.getExternalStorageDirectory(),filename+".png");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            try {
                fos.write(baos.toByteArray());
                fos.flush();
                fos.close();
            } catch (IOException e) {
                //BAFLogger.e(TAG,e.getMessage());
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
          //  BAFLogger.e(TAG,e.getMessage());
            e.printStackTrace();
        }
        recycleBitmap(bitmap);
        return file;
    }


    public static void recycleBitmap(Bitmap... bitmaps) {
        if (bitmaps==null) {
            return;
        }
        for (Bitmap bm : bitmaps) {
            if (null != bm && !bm.isRecycled()) {
                bm.recycle();
            }
        }
    }


    private List mPhotoList = new ArrayList();
    /**
     * 处理图片结果
     */
    public GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            if (resultList != null) {
                mPhotoList.clear();
                mPhotoList.addAll(resultList);
                PhotoInfo photoInfo = resultList.get(0);

                File headPhoto = new File(photoInfo.getPhotoPath());

                Glide.with(mContext)
                        .load(headPhoto)
                        .centerCrop()
                        .override(R.dimen.width_45, R.dimen.height_45)
                        .into(mCivMineInfo);
                //上传图片到服务器
                modifyAvatar(headPhoto);

            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
//            ToastUtils.makePicTextShortToast(mContext, Constants.ICON_ERROR, errorMsg);
        }
    };

    /**
     * 修改用户头像
     *
     * @param avatar 头像file
     */
    private void modifyAvatar(final File avatar) {
        showProgress();
        OkHttpUtils.post()
                .url(Network.User.USER_MODIFY_AVATAR)
                .addParams(Network.Param.TOKEN, mUser.getToken())
                .addFile(Network.Param.AVATAR, avatar.getName(), avatar)
                .build()
                .execute(new DCallback<Url>() {
                    @Override
                    public void onError(Call call, Exception e) {
                        connectError();

                    }

                    @Override
                    public void onResponse(Url response) {
                        if (isSuccess(response)) {
                            // 修改成功，现实头像
                            Glide.with(mContext)
                                    .load(avatar)
                                    .into(mCivMineInfo);
                            // 存在本地
                            mSpUtils.putString(SPUtils.UserKey.AVATAR, response.getUrl());
//                            mSpUtils.putString("user_head_path", avatar.getAbsolutePath());
                            ToastUtils.showShortToast(mContext, mContext.getResources().getString(R.string.modify_success));

                            Intent intent = new Intent(Constants.ACTION_AVATAR_MODIFY_SUCCESS);
                            intent.putExtra(Constants.URL, avatar.getAbsolutePath());

                            RxBus.getDefault().post(intent);


                        }
                        closeProgress();
                    }
                });
    }

    /**
     * 验证修改后的个人信息
     */
    private void confirmModify() {
        requestFileG = MultipartBody.Part.createFormData(Network.Param.TOKEN,  mUser.getToken());
        //昵称
        final String nickname = mEtMineInfoNickname.getText().toString();
        if (nickname.length() >= 2) {// 长度大于等于2，且和原来的不一样
            if (!TextUtils.equals(mUser.getNickname(), nickname)) {
                requestFileA = MultipartBody.Part.createFormData(Network.Param.NICKNAME, nickname);
            }
        } else {
            ToastUtils.makePicTextShortToast(mContext, "昵称长度不在接受范围内，昵称应为2至20位");
            return;
        }
        // 性别
        String gender = mTvMineInfoGender.getText().toString();
        final String gender1;
        if (!(UIUtils.getString(R.string.please_choose).equals(gender))) {
            if (UIUtils.getString(R.string.gender_male).equals(gender)) {
                gender1 = GENDER_MALE;
            } else {
                gender1 = GENDER_FEMALE;
            }
        } else {
            gender1 = GENDER;
        }
        requestFileB = MultipartBody.Part.createFormData(Network.Param.GENDER, gender1);
        // 生日
        String birthday = mTvMineInfoBirthday.getText().toString();
        final String birthdayTime;
        if (!(UIUtils.getString(R.string.please_choose).equals(birthday))) {
            birthdayTime = String.valueOf(DateFormatUtil.getDate4StrDate(birthday, "yyyy-MM-dd").getTime() / 1000);
            requestFileC = MultipartBody.Part.createFormData(Network.Param.BIRTHDAY,  birthdayTime);
        } else {
            birthdayTime = "";
        }
        // 所在地
        String city = mTvMineInfoCity.getText().toString();
        if (!(UIUtils.getString(R.string.please_choose).equals(city))) {
            if (cityId != null) {
                requestFileD = MultipartBody.Part.createFormData(Network.Param.REGISTERED, cityId);
            }
        }
        // 手机
        final String mobile = mEtMineInfoMobile.getText().toString().trim();
        if (mobileFormat(mobile)) {
            requestFileE = MultipartBody.Part.createFormData(Network.Param.MOBILE, mobile);
        } else {
            return;
        }
        // 签名
        final String sign = mEtMineInfoSign.getText().toString().trim();
        if (!TextUtils.equals(mUser.getSignature(), sign)) {
            requestFileF = MultipartBody.Part.createFormData(Network.Param.SIGNATURE,  sign);
        }

        showProgress();

        //用retrofit代替原有网络进行请求
         Retrofit_RequestUtils.getRequest().changeMineInfo(requestFileA, requestFileB, requestFileC, requestFileD, requestFileE, requestFileF, requestFileG)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DelectCollection>() {
                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        closeProgress();
                        Toast.makeText(mContext, "网络出错", Toast.LENGTH_SHORT).show();
                        refreshOrLoadFail();
                    }

                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(DelectCollection delectCollection) {
                        if (isSuccess(delectCollection)) {
                            if (delectCollection.getContent().isIs_ok()) {
                                ToastUtils.showShortToast(mContext, UIUtils.getString(R.string.modify_success));
                                saveMineInfo(nickname, gender1, birthdayTime, cityId, mobile, sign);

                                Intent intent = new Intent(Constants.ACTION_MINE_INFO_MODIFY_SUCCESS);
                                intent.putExtra(Constants.MOBILE, mobile);
                                intent.putExtra(Constants.NAME, nickname);

                               // LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);

                                RxBus.getDefault().post(intent);

                                mContext.finish();
                            } else {
                                ToastUtils.showShortToast(mContext, "修改失败，原因：没有修改任何信息");
                            }
                        }
                    }
                });

    }

    private void saveMineInfo(String nickname, String gender, String birthday, String city, String mobile, String sign) {
        if (nickname != null && !TextUtils.isEmpty(nickname)) {
            mSpUtils.putString(SPUtils.UserKey.NICKNAME, nickname);
        }
        if (gender != null && !TextUtils.isEmpty(gender)) {
            mSpUtils.putString(SPUtils.UserKey.GENDER, gender);
        }
        if (birthday != null && !TextUtils.isEmpty(birthday)) {
            mSpUtils.putString(SPUtils.UserKey.BIRTHDAY, birthday);
        }
        if (city != null && !TextUtils.isEmpty(city)) {
            mSpUtils.putString(SPUtils.UserKey.REGISTERED, city);
        }
        if (mobile != null && !TextUtils.isEmpty(mobile)) {
            mSpUtils.putString(SPUtils.UserKey.MOBILE, mobile);
        }
        if (sign != null ) {
            mSpUtils.putString(SPUtils.UserKey.SIGNATURE, sign);
        }
    }

    /**
     * 验证手机号的正确性
     *
     * @param mobile
     * @return
     */
    public boolean mobileFormat(String mobile) {
        if (TextUtils.isEmpty(mobile)) {
            ToastUtils.showShortToast(mContext, "请输入手机号");
            return false;
        }
        if (mobile.length() < 11) {
            ToastUtils.showShortToast(mContext, "手机号长度不正确");
            return false;
        }
        String telRegex = "[1][3578]\\d{9}";
        if (!mobile.matches(telRegex)) {
            ToastUtils.showShortToast(mContext, "请输入正确的手机号");
            return false;
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (handler != null) {
            handler = null;
        }
    }
}