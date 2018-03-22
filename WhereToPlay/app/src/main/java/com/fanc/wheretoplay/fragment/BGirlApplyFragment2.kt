package com.fanc.wheretoplay.fragment

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.*
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Build.*
import android.os.Bundle
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.text.TextUtils
import android.text.format.DateFormat
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
import com.bumptech.glide.Glide
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.DisplayActivity
import com.fanc.wheretoplay.base.App
import com.fanc.wheretoplay.base.BaseFragment
import com.fanc.wheretoplay.datamodel.AccessOrderIdModel
import com.fanc.wheretoplay.image.GlideGalleryImageLoader
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils
import com.fanc.wheretoplay.util.*
import com.fanc.wheretoplay.util.BitmapUtils.Companion.Bitmap2StrByBase64
import com.fanc.wheretoplay.util.BitmapUtils.Companion.compressImage
import com.fanc.wheretoplay.view.ModifyHeadDialog
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.bgirlapplyfragment2.*

import kotlinx.android.synthetic.main.bgirlinfo.*
import kotlinx.android.synthetic.main.bgirltitle.*
import okhttp3.MultipartBody
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


/**
 *
 * @author admin
 * @date 2018/3/16
 */
class BGirlApplyFragment2 : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv1 -> {
                ivDsplay = "1"
                modifyHeader()
            }
            R.id.iv2 -> {
                ivDsplay = "2"
                modifyHeader()

                Toast.makeText(mContext, spi.data, Toast.LENGTH_SHORT).show()

            }
            R.id.iv3 -> {
                ivDsplay = "3"
                modifyHeader()

            }
            else -> {
            }
        }
    }

    private var iv164Value: String? = null
    var iv264Value: String? = null
    var iv364Value: String? = null

    var ivDsplay: String? = "-1"


    private fun modifyHeader() {
        if (VERSION.SDK_INT >= VERSION_CODES.M) {
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

    private val REQUEST_CODE_ALBUM = 0x1001
    private val REQUEST_CODE_CAMERA = 0x1002

    private fun selectIcon(which: Int) {
        when (which) {
            1 ->
                //检测是否有相机和读写文件权限
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    mContext.requestPermissions(arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), 10085)
                } else {
                    handleCameraPicture()
                }
            2 -> {
                handleSelectPicture()
            }
        }
    }


    /**
     * 选择相册图片
     *
     * @param flag
     */
    fun handleSelectPicture() {
        val intent: Intent
        if (Build.VERSION.SDK_INT < 19) {
            intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT

            intent.type = "image/jpeg"

            startActivityForResult(Intent.createChooser(intent, "选择图片"),
                    REQUEST_CODE_ALBUM)
        } else {
            intent = Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            //intent.setType("image/*");


            intent.type = "image/png,image/jpeg"
            startActivityForResult(Intent.createChooser(intent, "选择图片"),
                    REQUEST_CODE_ALBUM)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return View.inflate(inflater.context, R.layout.bgirlapplyfragment2, null)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tbv.setTv_title("信息登记")
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


        //获取所有的商家信息

      /*  Retrofit_RequestUtils.getRequest()
                .emplGetMessage(MultipartBody.Part.createFormData("token", SPUtils(context).getUser().getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object :Observer<>{

                })*/



        val list = arrayListOf<String>()
        list.add("a")
        list.add("天上人间")
        list.add("天地会")
        list.add("a")


        spi.setData(list, mContext)


        eev1.setData()
        eev2.setData()
        eev3.setData()
        eev4.setData()
        eev5.setData()
        eev6.setData()
        eev7.setData()
        eev8.setData()
        eev9.setData()
        eev10.setData()
        eev11.setData()
        eev12.setData()
        eev13.setData()
        eev14.setData()
        eev15.setData()
        eev16.setData()
        iv164Value = "122112"
        iv264Value = "122112"
        iv364Value = "122112"



        iv1.setOnClickListener(this)
        iv2.setOnClickListener(this)
        iv3.setOnClickListener(this)

        go.setOnClickListener {
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
                list.add(MultipartBody.Part.createFormData("store_id", SPUtils(context).getUser().getToken()))
                list.add(MultipartBody.Part.createFormData("user_id", SPUtils(context).getUser().id))



                list.add(MultipartBody.Part.createFormData("name", eev1.data))
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
                list.add(MultipartBody.Part.createFormData("id_pic_path1", iv264Value))//身份证正面
                list.add(MultipartBody.Part.createFormData("id_pic_path2", iv364Value))//身份证背面


                Retrofit_RequestUtils.getRequest()
                        .EmplRegistration(list)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Observer<AccessOrderIdModel> {
                            override fun onComplete() {
                                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                            override fun onSubscribe(p0: Disposable) {
                                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                            override fun onNext(p0: AccessOrderIdModel) {

                                val intent = Intent(mContext, DisplayActivity::class.java)
                                intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment3")
                                intent.putExtra("status", "-1")
                                startActivity(intent)
                            }

                            override fun onError(p0: Throwable) {
                                Toast.makeText(mContext, p0.toString(), Toast.LENGTH_SHORT).show()

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

                } else {
                    Toast.makeText(mContext, "改功能需要相机和读写文件权限", Toast.LENGTH_SHORT).show()
                }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {

                REQUEST_CODE_ALBUM -> {
                    if (data == null)
                        return
                    handleUriAndSend(data.data)
                }
                REQUEST_CODE_CAMERA ->


                    handleUriAndSend(camePhotoUri)
            }
        }
    }


    private fun handleUriAndSend(uriData: Uri?) {

        val sdStatus = Environment.getExternalStorageState()
        if (sdStatus != Environment.MEDIA_MOUNTED) { // 检测sd是否可用
            return
        }

        val name = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)).toString() + ".jpg"

        val RealFilePath = getRealFilePath(mContext, uriData)
        if (RealFilePath!!.endsWith("jpg") || RealFilePath!!.endsWith("jpeg") || RealFilePath!!.endsWith("png")) {
            val bitmap = BitmapUtils.getimage(RealFilePath)


            var b: FileOutputStream? = null
            Log.i("PATH", Environment.getExternalStorageDirectory().absolutePath)
            val file = File(Environment.getExternalStorageDirectory().absolutePath + "/")
            file.mkdirs()// 创建文件夹
            val filepath = Environment.getExternalStorageDirectory().absolutePath + "/" + name

            try {
                b = FileOutputStream(filepath)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b)// 把数据写入文件
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    if (b != null) {
                        b.flush()
                        b.close()
                    }

                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }


            val files = File(RealFilePath)


            setImageData(filepath, BitmapUtils.getimage(RealFilePath))

        } else {
            Toast.makeText(mContext, "图片格式错误请重新选择", Toast.LENGTH_SHORT).show()
        }
    }

    fun getRealFilePath(context: Context, uri: Uri?): String? {
        if (null == uri) return null
        val scheme = uri.scheme
        var data: String? = null
        if (scheme == null)
            data = uri.path
        else if (ContentResolver.SCHEME_FILE == scheme) {
            data = uri.path
        } else if (ContentResolver.SCHEME_CONTENT == scheme) {
            val cursor = context.contentResolver.query(uri, arrayOf(MediaStore.Images.ImageColumns.DATA), null, null, null)
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    val index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                    if (index > -1) {
                        data = cursor.getString(index)
                    }
                }
                cursor.close()
            }
        }
        return data
    }


    fun setImageData(headPhoto: String, bitmap: Bitmap) {
        // scrollView.scrollTo(ScrollView.)
        when (ivDsplay) {
            "1" -> {
                iv164Value = "data:image/png;base64,${Bitmap2StrByBase64(bitmap)}"

                Glide.with(App.mContext)
                        .load(headPhoto)
                        .into(iv1)
            }
            "2" -> {

                iv264Value = "data:image/png;base64,${Bitmap2StrByBase64(bitmap)}"

                Glide.with(App.mContext)
                        .load(headPhoto)
                        .into(iv2)

            }
            "3" -> {

                iv364Value = "data:image/png;base64,${Bitmap2StrByBase64(bitmap)}"
                Glide.with(App.mContext)
                        .load(headPhoto)
                        .into(iv3)

            }

            else -> {

            }
        }

    }


    var camePhotoUri: Uri? = null

    private fun handleCameraPicture() {

        // 判断是否挂载了SD卡
        var savePath = ""
        val storageState = Environment.getExternalStorageState()
        if (storageState == Environment.MEDIA_MOUNTED) {
            savePath = Environment.getExternalStorageDirectory()
                    .absolutePath + "/ssqs/Camera/"
            val savedir = File(savePath)
            if (!savedir.exists()) {
                savedir.mkdirs()
            }
        }

        // 没有挂载SD卡，无法保存文件
        if (TextUtils.isEmpty(savePath)) {
            // ToastUtil.show(EditUserInfoActivity.this, "无法保存照片，请检查SD卡是否挂载");
            // ToastUtils.showToast("无法保存照片，请检查SD卡是否挂载")
            return
        }

        val timeStamp = SimpleDateFormat("yyyyMMddHHmmss")
                .format(Date())
        val fileName = "ssqs$timeStamp.jpg"// 照片命名
        val out = File(Environment.getExternalStorageDirectory(), fileName)
        val uri: Uri
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION

            val contentValues = ContentValues(1)
            contentValues.put(MediaStore.Images.Media.DATA, out.absolutePath)
            uri = mContext.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        } else {
            uri = Uri.fromFile(out)
        }


        if (!TextUtils.isEmpty(uri.toString())) {
            camePhotoUri = uri
        } else {
            return
        }


        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        startActivityForResult(intent,
                REQUEST_CODE_CAMERA)

    }

}