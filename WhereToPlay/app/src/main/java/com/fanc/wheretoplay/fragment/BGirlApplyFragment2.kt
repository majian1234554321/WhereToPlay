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
import android.os.Build.*
import android.os.Bundle
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.fanc.wheretoplay.util.BitmapUtils.Companion.Bitmap2StrByBase64
import com.fanc.wheretoplay.util.BitmapUtils.Companion.compressImage
import com.fanc.wheretoplay.util.BitmapUtils.Companion.getBitmap
import com.fanc.wheretoplay.util.Constants
import com.fanc.wheretoplay.util.FileUtils
import com.fanc.wheretoplay.util.SPUtils
import com.fanc.wheretoplay.view.ModifyHeadDialog
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.bgirlapplyfragment2.*

import kotlinx.android.synthetic.main.bgirlinfo.*
import okhttp3.MultipartBody
import java.io.*



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


      private fun selectIcon(which: Int) {
           when (which) {
               1 ->
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

       private var contentUri: Uri? = null


       private fun startCamera() {
           val imagePath = File(Environment.getExternalStorageDirectory(), "images")
           if (!imagePath.exists()) imagePath.mkdirs()
           val newFile = File(imagePath, "default_image.jpg")

           //第二参数是在manifest.xml定义 provider的authorities属性
           contentUri = FileProvider.getUriForFile(mContext, "com.fanc.wheretoplay.camera_photos", newFile)

           val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
           //兼容版本处理，因为 intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION) 只在5.0以上的版本有效
           if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
               intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
           } else if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
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


       private var mOnHanlderResultCallback: GalleryFinal.OnHanlderResultCallback = object : GalleryFinal.OnHanlderResultCallback {
           override fun onHanlderSuccess(reqeustCode: Int, resultList: List<PhotoInfo>?) {
               if (resultList != null) {

                   val photoInfo = resultList[0]

                   val headPhoto = File(photoInfo.photoPath)


                   setImageData(headPhoto,getBitmap(photoInfo.photoPath))



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
       // tv2.setTextColor(Color.parseColor("#c4483c"))
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
                              startActivity(intent)
                          }

                          override fun onError(p0: Throwable) {
                             // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val contentProvider = mContext.contentResolver

            val mInputPFD: ParcelFileDescriptor?
            try {
                //获取contentProvider图片
                mInputPFD = contentProvider.openFileDescriptor(contentUri, "r")

                val fileDescriptor = mInputPFD!!.fileDescriptor



                val headPhoto = compressImage(BitmapFactory.decodeFileDescriptor(fileDescriptor))

                setImageData(headPhoto,BitmapFactory.decodeFileDescriptor(fileDescriptor))


            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

        }
    }


    fun setImageData(headPhoto:File,bitmap: Bitmap){
        when (ivDsplay) {
            "1" -> {
                Glide.with(App.mContext)
                        .load(headPhoto)
                        .into(iv1)
                iv164Value = Bitmap2StrByBase64(bitmap)
            }
            "2" -> {
                Glide.with(App.mContext)
                        .load(headPhoto)
                        .into(iv2)
                iv264Value = Bitmap2StrByBase64(bitmap)
            }
            "3" -> {
                Glide.with(App.mContext)
                        .load(headPhoto)
                        .into(iv3)
                iv364Value = Bitmap2StrByBase64(bitmap)
            }

            else -> {

            }
        }

    }







    private fun initGallery() {
        //设置主题
        val themeConfig = ThemeConfig.DEFAULT
        //配置功能
        val functionConfigBuilder = FunctionConfig.Builder()
        functionConfigBuilder
                .setEnableCamera(true)// 使用相机
                .setEnableEdit(true)// 编辑
                .setEnableCrop(false)// 裁剪
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
        val coreConfig = CoreConfig.Builder(App.getContext(), imageLoader, themeConfig)
                .setEditPhotoCacheFolder(editCacheFile)
                .setTakePhotoFolder(cacheFile)
                .setFunctionConfig(functionConfig)
                .build()
        GalleryFinal.init(coreConfig)
    }
}