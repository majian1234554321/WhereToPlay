package com.fanc.wheretoplay.fragment

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.ClipData
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.Toast
import cn.finalteam.galleryfinal.CoreConfig
import cn.finalteam.galleryfinal.FunctionConfig
import cn.finalteam.galleryfinal.GalleryFinal
import cn.finalteam.galleryfinal.ThemeConfig
import cn.finalteam.galleryfinal.model.PhotoInfo
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.DisplayActivity
import com.fanc.wheretoplay.base.BaseFragment
import com.fanc.wheretoplay.datamodel.AccessOrderIdModel
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.bgirlapplyfragment2.*
import kotlinx.android.synthetic.main.bgirlinfo.*
import kotlinx.android.synthetic.main.bgirltitle.*
import okhttp3.MultipartBody

import com.bumptech.glide.Glide
import com.fanc.wheretoplay.base.App
import com.fanc.wheretoplay.image.GlideGalleryImageLoader
import com.fanc.wheretoplay.util.Constants
import com.fanc.wheretoplay.util.FileUtils
import com.fanc.wheretoplay.util.SPUtils
import com.fanc.wheretoplay.view.ModifyHeadDialog
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


/**
 *
 * @author admin
 * @date 2018/3/16
 */
class BGirlApplyFragment2 : BaseFragment(), View.OnClickListener {
    private var iv164Value: String? = null
    var iv264Value: String? = null
    var iv364Value: String? = null

    var ivDsplay: String? = "-1"

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv1 -> {
                ivDsplay = "1"
                modifyHeader()
            }
            R.id.iv2 -> {
                ivDsplay = "2"
                modifyHeader()

            }
            R.id.iv3 -> {
                ivDsplay = "3"
                modifyHeader()

            }
            else -> {
            }
        }
    }


    private fun modifyHeader() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
            for (permission in permissions) {
                if (ContextCompat.checkSelfPermission(mContext, permission) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(mContext, permissions, Constants.REQUEST_PERMISSION_CODE)
                }
            }
        }
        ModifyHeadDialog(mContext)
                .setTakePhotoOnClickListener { selectIcon(1) }
                .setGalleryOnClickListener { selectIcon(2) }
                .setCanceledOnTouchOutside(true)
                .show()
    }


    fun selectIcon(which: Int) {
        when (which) {
            1 //拍照
            ->
                // GalleryFinal.openCamera(1001, mOnHanlderResultCallback);

                //检测是否有相机和读写文件权限
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    mContext.requestPermissions(arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), 10085)
                } else {
                    startCamera()
                }
            2 //打开相册
            -> GalleryFinal.openGallerySingle(1002, mOnHanlderResultCallback)
        }
    }

    var contentUri: Uri? = null

    private fun startCamera() {
        val imagePath = File(Environment.getExternalStorageDirectory(), "images")
        if (!imagePath.exists()) imagePath.mkdirs()
        val newFile = File(imagePath, "default_image.jpg")

        //第二参数是在manifest.xml定义 provider的authorities属性
        contentUri = FileProvider.getUriForFile(mContext, "com.fanc.wheretoplay.camera_photos", newFile)

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        //兼容版本处理，因为 intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION) 只在5.0以上的版本有效
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            val clip = ClipData.newUri(mContext.contentResolver, "A photo", contentUri)
            intent.clipData = clip
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        } else {
            val resInfoList = mContext.packageManager
                    .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
            for (resolveInfo in resInfoList) {
                val packageName = resolveInfo.activityInfo.packageName
                mContext.grantUriPermission(packageName, contentUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            }
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri)

        startActivityForResult(intent, 1000)
    }


    var mOnHanlderResultCallback: GalleryFinal.OnHanlderResultCallback = object : GalleryFinal.OnHanlderResultCallback {
        override fun onHanlderSuccess(reqeustCode: Int, resultList: List<PhotoInfo>?) {
            if (resultList != null) {

                val photoInfo = resultList[0]

                val headPhoto = File(photoInfo.photoPath)

                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                when (ivDsplay) {
                    "1" -> {
                        Glide.with(App.mContext)
                                .load(headPhoto)
                                .into(iv1)
                        iv164Value = Bitmap2StrByBase64(getBitmap(photoInfo.photoPath))
                    }
                    "2" -> {
                        Glide.with(App.mContext)
                                .load(headPhoto)
                                .into(iv2)
                        iv264Value = Bitmap2StrByBase64(getBitmap(photoInfo.photoPath))
                    }
                    "3" -> {
                        Glide.with(App.mContext)
                                .load(headPhoto)
                                .into(iv3)
                        iv364Value = Bitmap2StrByBase64(getBitmap(photoInfo.photoPath))
                    }

                    else -> {
                        iv1
                    }
                }


            }
        }

        override fun onHanlderFailure(requestCode: Int, errorMsg: String) {

        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = View.inflate(inflater.context, R.layout.bgirlapplyfragment2, null)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGallery()
        tbv.setTv_title("从业申请")
        tv2.setTextColor(Color.parseColor("#c4483c"))
        eev1.setTv("单位名称", true)
        eev2.setTv("姓名", true)
        eev3.setTv("性别", true)
        eev4.setTv("出生日期", true)
        eev5.setTv("民族", true)
        eev6.setTv("证件类型", true)
        eev7.setTv("证件号", true)
        eev8.setTv("户籍地址", true)
        eev9.setTv("详细地址", true)
        eev10.setTv("现住地址", true)
        eev11.setTv("收货地址", true)
        eev12.setTv("本人手机号", true)
        eev13.setTv("紧急联系人", true)
        eev14.setTv("联系人电话", true)
        eev15.setTv("行业", true)
        eev16.setTv("职务", true)

        iv1.setOnClickListener(this)
        iv2.setOnClickListener(this)
        iv3.setOnClickListener(this)

        next.setOnClickListener {
            if (!TextUtils.isEmpty(eev1.data) &&
                    !TextUtils.isEmpty(eev1.data) &&
                    !TextUtils.isEmpty(eev2.data) &&
                    !TextUtils.isEmpty(eev3.data) &&
                    !TextUtils.isEmpty(eev4.data) &&
                    !TextUtils.isEmpty(eev5.data) &&
                    !TextUtils.isEmpty(eev6.data) &&
                    !TextUtils.isEmpty(eev7.data) &&
                    !TextUtils.isEmpty(eev8.data) &&
                    !TextUtils.isEmpty(eev9.data) &&
                    !TextUtils.isEmpty(eev10.data) &&
                    !TextUtils.isEmpty(eev11.data) &&
                    !TextUtils.isEmpty(eev12.data) &&
                    !TextUtils.isEmpty(eev13.data) &&
                    !TextUtils.isEmpty(eev14.data) &&
                    !TextUtils.isEmpty(eev16.data) &&
                    !TextUtils.isEmpty(eev15.data) &&
                    !TextUtils.isEmpty(iv164Value) &&
                    !TextUtils.isEmpty(iv264Value) &&
                    !TextUtils.isEmpty(iv364Value)
            ) {


                val list = arrayListOf<MultipartBody.Part>()

                list.add(MultipartBody.Part.createFormData("token", SPUtils(context).getUser().getToken()))
                list.add(MultipartBody.Part.createFormData("store_name", eev1.data))
                list.add(MultipartBody.Part.createFormData("id", eev2.data))
                list.add(MultipartBody.Part.createFormData("sex", eev3.data))//sex
                list.add(MultipartBody.Part.createFormData("birthday", eev4.data))//生日
                list.add(MultipartBody.Part.createFormData("ethnic", eev5.data))//民族
                list.add(MultipartBody.Part.createFormData("id_type", eev6.data))//证件类型 1身份证 2 其他
                list.add(MultipartBody.Part.createFormData("id_number", eev7.data))//身份证号
                list.add(MultipartBody.Part.createFormData("birth_address", eev8.data))//户籍地址
                list.add(MultipartBody.Part.createFormData("birthdetailaddress", eev9.data))// 户籍详细地址
                list.add(MultipartBody.Part.createFormData("nowdetailaddress", eev10.data))//现住地址
                list.add(MultipartBody.Part.createFormData("harvest_address", eev11.data))//现住详细地址
                list.add(MultipartBody.Part.createFormData("mobile", eev12.data))//手机号
                list.add(MultipartBody.Part.createFormData("urgent_man", eev13.data)) //紧急联系人
                list.add(MultipartBody.Part.createFormData("urgent_mobile", eev14.data))//紧急联系人号码
                list.add(MultipartBody.Part.createFormData("profession", eev15.data))//行业
                list.add(MultipartBody.Part.createFormData("position", eev16.data))//职务


                list.add(MultipartBody.Part.createFormData("personal_path", iv164Value))//个人照片
                list.add(MultipartBody.Part.createFormData("idpicpath1", iv264Value))//身份证正面
                list.add(MultipartBody.Part.createFormData("idpicpath2", iv364Value))//身份证背面


                Retrofit_RequestUtils.getRequest()
                        .EmplRegistration(list).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Observer<AccessOrderIdModel> {
                            override fun onComplete() {
                                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                            override fun onSubscribe(p0: Disposable) {
                                // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                            override fun onNext(p0: AccessOrderIdModel) {
                                val intent = Intent(mContext, DisplayActivity::class.java)
                                intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment3")
                                startActivity(intent)
                            }

                            override fun onError(p0: Throwable) {
                                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                        })


            } else {
                Toast.makeText(mContext, "数据信息不完整", Toast.LENGTH_SHORT).show()
            }

        }


    }

    companion object {
        fun newInstance(): BGirlApplyFragment2 {
            return BGirlApplyFragment2()
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            10085 ->
                // 如果权限被拒绝，grantResults 为空
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startCamera()
                } else {
                    Toast.makeText(mContext, "改功能需要相机和读写文件权限", Toast.LENGTH_SHORT).show()
                }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK) {
            val contentProvider = mContext.contentResolver

            val mInputPFD: ParcelFileDescriptor?
            try {
                //获取contentProvider图片
                mInputPFD = contentProvider.openFileDescriptor(contentUri, "r")

                val fileDescriptor = mInputPFD!!.fileDescriptor


                val headPhoto = compressImage(BitmapFactory.decodeFileDescriptor(fileDescriptor))
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                when (ivDsplay) {
                    "1" -> {

                        Glide.with(App.mContext)
                                .load(headPhoto)
                                .into(iv1)
                        iv164Value = Bitmap2StrByBase64(BitmapFactory.decodeFileDescriptor(fileDescriptor))
                    }
                    "2" -> {
                        Glide.with(App.mContext)
                                .load(headPhoto)
                                .into(iv2)
                        iv264Value = Bitmap2StrByBase64(BitmapFactory.decodeFileDescriptor(fileDescriptor))
                    }
                    "3" -> {
                        Glide.with(App.mContext)
                                .load(headPhoto)
                                .into(iv3)
                        iv364Value = Bitmap2StrByBase64(BitmapFactory.decodeFileDescriptor(fileDescriptor))
                    }

                    else -> {
                        iv1
                    }
                }

            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

        }
    }

    fun compressImage(bitmap: Bitmap): File {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        var options = 100
        while (baos.toByteArray().size / 1024 > 500) {  //循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset()//重置baos即清空baos
            options -= 10//每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos)//这里压缩options%，把压缩后的数据存放到baos中
            val length = baos.toByteArray().size.toLong()
        }
        val format = SimpleDateFormat("yyyyMMddHHmmss")
        val date = Date(System.currentTimeMillis())
        val filename = format.format(date)
        val file = File(Environment.getExternalStorageDirectory(), "$filename.png")
        try {
            val fos = FileOutputStream(file)
            try {
                fos.write(baos.toByteArray())
                fos.flush()
                fos.close()
            } catch (e: IOException) {
                //BAFLogger.e(TAG,e.getMessage());
                e.printStackTrace()
            }

        } catch (e: FileNotFoundException) {
            //  BAFLogger.e(TAG,e.getMessage());
            e.printStackTrace()
        }

        recycleBitmap(bitmap)
        return file
    }


    fun recycleBitmap(vararg bitmaps: Bitmap) {

        for (bm in bitmaps) {
            if (!bm.isRecycled) {
                bm.recycle()
            }
        }
    }

    /**
     * 初始化Gallery
     */
    private fun initGallery() {
        //设置主题
        val themeConfig = ThemeConfig.DEFAULT
        //配置功能
        val functionConfigBuilder = FunctionConfig.Builder()
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
                .setEnableRotate(true)
        val functionConfig = functionConfigBuilder.build()
        val imageLoader = GlideGalleryImageLoader()
        val editCacheFile = File(FileUtils.getIconDir() + "/edit")
        val cacheFile = File(FileUtils.getIconDir())

        Log.d("aaa", "initGallery: editCacheFile =" + FileUtils.getIconDir() + "/edit" + "\t cacheFile = " + FileUtils.getIconDir())
        val coreConfig = CoreConfig.Builder(mContext, imageLoader, themeConfig)
                .setEditPhotoCacheFolder(editCacheFile)
                .setTakePhotoFolder(cacheFile)
                .setFunctionConfig(functionConfig)
                .build()
        GalleryFinal.init(coreConfig)
    }


    private fun getBitmap(srcPath: String): Bitmap {
        val newOpts = BitmapFactory.Options()
        // 开始是先把newOpts.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true
        var bitmap = BitmapFactory.decodeFile(srcPath, newOpts) // 此时返回bitmap为null

        newOpts.inJustDecodeBounds = false
        val w = newOpts.outWidth
        val h = newOpts.outHeight
        // 以800*480分辨率为例
        val hh = 800f // 这里设置高度为800f
        val ww = 480f // 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        var scale = 1 // be=1表示不缩放
        if (w > h && w > ww) { // 如果宽度大的话根据宽度固定大小缩放
            scale = (newOpts.outWidth / ww).toInt()
        } else if (w < h && h > hh) { // 如果高度高的话根据宽度固定大小缩放
            scale = (newOpts.outHeight / hh).toInt()
        }
        if (scale <= 0) scale = 1
        newOpts.inSampleSize = scale // 设置缩放比例 // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts)
        return bitmap
    }

    fun Bitmap2StrByBase64(bit: Bitmap): String {

        val bos = ByteArrayOutputStream()
        bit.compress(Bitmap.CompressFormat.JPEG, 40, bos) // 参数100表示不压缩
        // bit.compress(Bitmap.CompressFormat., 40, bos);
        val bytes = bos.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

}