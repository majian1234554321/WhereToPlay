package com.fanc.wheretoplay.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.*
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.*
import android.os.Build.*
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
import android.widget.*

import com.bumptech.glide.Glide
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.DisplayActivity
import com.fanc.wheretoplay.base.App
import com.fanc.wheretoplay.base.BaseFragment
import com.fanc.wheretoplay.datamodel.*
import com.fanc.wheretoplay.image.GlideGalleryImageLoader
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils
import com.fanc.wheretoplay.util.*
import com.fanc.wheretoplay.util.BitmapUtils.Companion.Bitmap2StrByBase64
import com.fanc.wheretoplay.util.BitmapUtils.Companion.compressImage
import com.fanc.wheretoplay.view.ModifyHeadDialog
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.bgirlapplyfragment2_1.*
import kotlinx.android.synthetic.main.bgirlinfo.*
import kotlinx.android.synthetic.main.bgirltitle.*

import okhttp3.MultipartBody
import org.json.JSONArray
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


/**
 *
 * @author admin
 * @date 2018/3/16
 */
class BGirlApplyFragment2_1 : BaseFragment() {
    /* override fun onClick(v: View?) {
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
     }*/

    private var iv164Value: String? = null
    var iv264Value: String? = null
    var iv364Value: String? = null
    var store_id: String? = null

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

        val view = View.inflate(inflater.context, R.layout.bgirlapplyfragment2_1, null)



        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (arguments?.getString("bgirltype") == "emplYearReviewStatus") {
            tbv.setTv_title("年审登记")
            go.text = "提交年审"
        } else {
            tbv.setTv_title("补卡登记")
            go.text = "提交补卡"
        }

        tv2.setTextColor(Color.parseColor("#c4483c"))



        eev1.setTv("单位名称", true)
        eev2.setTv("姓名", true)
        eev3.setTv("性别", true)

        eev4.setTv("出生日期", true)
        eev5.setTv("民族", true)
        eev5.visibility= View.GONE

        eev7.setTv("身份证号", true)
        eev8.setTv("户籍地址", true)
        eev9.setTv("详细地址", true)
        eev10.setTv("现住地址", true)
        eev11.setTv("收货地址", true)
        eev12.setTv("本人手机号", true)
        eev13.setTv("紧急联系人", true)
        eev14.setTv("联系人电话", true)
        eev15.setTv("行业", true)
        eev16.setTv("职务", true)

        mHandler.sendEmptyMessage(MSG_LOAD_DATA)


        val list1 = arrayListOf<String>()

        val list5 = arrayListOf<String>()
        list5.add("汉")
        list5.add("回")


        val list15 = arrayListOf<String>();
        list15.add("歌舞娱乐")
        list15.add("酒吧")
        list15.add("音乐茶室")
        list15.add("游戏机")
        list15.add("游艺机")
        list15.add("文化游乐场（游乐园）")
        list15.add("棋牌室")
        list15.add("浴场浴室")
        list15.add("桑拿会所")
        list15.add("按摩保健")
        list15.add("足浴")
        list15.add("美容美发（含桑拿）")


        val list16 = arrayListOf<String>()
        list16.add("A级经营负责人")
        list16.add("B级行政人员")
        list16.add("C级业务主管")
        list16.add("D级服务生")
        list16.add("E级服务员")
        list16.add("其他")


        //获取所有的商家信息

        Retrofit_RequestUtils.getRequest()
                .emplDetail(MultipartBody.Part.createFormData("token", SPUtils(context).getUser().getToken()),
                        MultipartBody.Part.createFormData("application_id", arguments?.getString("application_id")))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<EmplDetailModel> {
                    override fun onComplete() = Unit

                    override fun onSubscribe(d: Disposable) = Unit

                    override fun onNext(t: EmplDetailModel) {


                        if (t.code == "0") {


                            with(t.content) {


                                eev1.data = store_name
                                eev2.data = name


                                eev3.data = if (sex == "1") "男" else "女"

                                eev4.data = DateFormatUtil.stampToDateMM(birthday)
                                eev5.data = ethnic

                                eev7.data = id_number
                                eev8.data = birth_address
                                eev9.data = birth_detail_address
                                eev10.data = now_address
                                eev11.data = now_detail_address
                                eev12.data = mobile
                                eev13.data = urgent_man
                                eev14.data = urgent_mobile
                                eev15.data = profession
                                eev16.data = position





                                iv164Value = personal_path
                                iv264Value = ic_pic_path1
                                iv364Value = ic_pic_path2


                                Glide.with(mContext).load(personal_path).into(iv1)
                                Glide.with(mContext).load(ic_pic_path1).into(iv2)
                                Glide.with(mContext).load(ic_pic_path2).into(iv3)


                            }
                        } else {
                            Toast.makeText(mContext, t.message, Toast.LENGTH_SHORT).show()
                            mContext.finish()
                        }

                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show()
                    }

                })


//        iv1.setOnClickListener(this)
//        iv2.setOnClickListener(this)
//        iv3.setOnClickListener(this)

        go.setOnClickListener {
            if (!TextUtils.isEmpty(eev1.data) &&

                    !TextUtils.isEmpty(eev2.data) &&

                    !TextUtils.isEmpty(eev4.data) &&
                    !TextUtils.isEmpty(eev5.data) &&

                    !TextUtils.isEmpty(eev7.data) &&
                    !TextUtils.isEmpty(eev8.data) &&
                    !TextUtils.isEmpty(eev9.data) &&
                    !TextUtils.isEmpty(eev10.data) &&
                    !TextUtils.isEmpty(eev11.data) &&
                    !TextUtils.isEmpty(eev12.data) &&
                    !TextUtils.isEmpty(eev13.data) &&
                    !TextUtils.isEmpty(eev14.data) &&
                    !TextUtils.isEmpty(eev15.data) &&
                    !TextUtils.isEmpty(eev16.data)


            ) {

                val listArgs = arrayListOf<MultipartBody.Part>()

                listArgs.add(MultipartBody.Part.createFormData("token", SPUtils(context).getUser().getToken()))
                listArgs.add(MultipartBody.Part.createFormData("store_name", eev1.data))

                listArgs.add(MultipartBody.Part.createFormData("name", eev2.data))


                listArgs.add(MultipartBody.Part.createFormData("application_id", arguments?.getString("application_id")))



                listArgs.add(MultipartBody.Part.createFormData("name", if (eev3.data == "男") "1" else "2"))



                listArgs.add(MultipartBody.Part.createFormData("birthday", eev4.data))//生日

                listArgs.add(MultipartBody.Part.createFormData("ethnic", eev5.data))//民族
                // listArgs.add(MultipartBody.Part.createFormData("id_type", eev6.data))//证件类型 1身份证 2 其他
                listArgs.add(MultipartBody.Part.createFormData("id_number", eev7.data))//身份证号
                listArgs.add(MultipartBody.Part.createFormData("birth_address", eev8.data))//户籍地址
                listArgs.add(MultipartBody.Part.createFormData("birth_detail_address", eev9.data))// 户籍详细地址
                listArgs.add(MultipartBody.Part.createFormData("now_address", eev10.data))//现住地址
                listArgs.add(MultipartBody.Part.createFormData("now_detail_address", eev10.data))//现住详细地址
                listArgs.add(MultipartBody.Part.createFormData("harvest_address", eev11.data))//收获地址
                listArgs.add(MultipartBody.Part.createFormData("mobile", eev12.data))//手机号
                listArgs.add(MultipartBody.Part.createFormData("urgent_man", eev13.data)) //紧急联系人
                listArgs.add(MultipartBody.Part.createFormData("urgent_mobile", eev14.data))//紧急联系人号码
                listArgs.add(MultipartBody.Part.createFormData("profession", eev15.data))//行业
                listArgs.add(MultipartBody.Part.createFormData("position", eev16.data))//职务






                listArgs.add(MultipartBody.Part.createFormData("type", if (arguments?.getString("bgirltype") == "emplYearReviewStatus") "3" else "2"))


                Retrofit_RequestUtils.getRequest()
                        .emplRegistration(listArgs)
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

                                if (p0.code == "0") {
                                    val intent = Intent(mContext, DisplayActivity::class.java)
                                    intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment3")
                                    intent.putExtra("bgirltype", arguments?.getString("bgirltype"))
                                    intent.putExtra("statues", "1")
                                    intent.putExtra("application_id", arguments?.getString("application_id"))
                                    startActivity(intent)
                                    mContext.finish()
                                } else {
                                    Toast.makeText(mContext, p0.message, Toast.LENGTH_SHORT).show()
                                }


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
        fun newInstance(bgirltype: String,application_id:String): BGirlApplyFragment2_1 {
            val bGirlApplyFragment2 = BGirlApplyFragment2_1()
            val args = Bundle()


            args.putString("bgirltype", bgirltype)
            args.putString("application_id", application_id)

            bGirlApplyFragment2.arguments = args

            return bGirlApplyFragment2
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

    private fun getTime(date: Date): String {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.time)
        val format = SimpleDateFormat("yyyy-MM-dd")
        return format.format(date)
    }


    fun parseData(result: String): ArrayList<JsonBean> {//Gson 解析
        val detail = ArrayList<JsonBean>()
        try {
            val data = JSONArray(result)
            val gson = Gson()
            for (i in 0 until data.length()) {
                val entity = gson.fromJson<JsonBean>(data.optJSONObject(i).toString(), JsonBean::class.java!!)
                detail.add(entity)
            }
        } catch (e: Exception) {
            e.printStackTrace()

        }

        return detail
    }


    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_LOAD_DATA -> if (thread == null) {//如果已创建就不再重新创建子线程了

                    // Toast.makeText(mContext, "Begin Parse Data", Toast.LENGTH_SHORT).show()
                    thread = Thread(Runnable {
                        // 子线程中解析省市区数据
                        initJsonData()
                    })
                    thread!!.start()
                }

                MSG_LOAD_SUCCESS -> {
                    // Toast.makeText(mContext, "Parse Succeed", Toast.LENGTH_SHORT).show()
                    isLoaded = true
                }


            }
        }
    }

    private val MSG_LOAD_DATA = 0x0001
    private val MSG_LOAD_SUCCESS = 0x0002
    private val MSG_LOAD_FAILED = 0x0003

    private var isLoaded = false

    var thread: Thread? = null


    private fun initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         */
        val JsonData = GetJsonDataUtil().getJson(mContext, "province.json")//获取assets目录下的json文件数据

        val jsonBean = parseData(JsonData)//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean

        for (i in jsonBean.indices) {//遍历省份
            val CityList = ArrayList<String>()//该省的城市列表（第二级）
            val Province_AreaList = ArrayList<ArrayList<String>>()//该省的所有地区列表（第三极）

            for (c in 0 until jsonBean[i].cityList.size) {//遍历该省份的所有城市
                val CityName = jsonBean[i].cityList[c].name
                CityList.add(CityName)//添加城市
                val City_AreaList = ArrayList<String>()//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean[i].cityList[c].area == null || jsonBean[i].cityList[c].area.size === 0) {
                    City_AreaList.add("")
                } else {
                    City_AreaList.addAll(jsonBean[i].cityList[c].area)
                }
                Province_AreaList.add(City_AreaList)//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList)

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList)
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS)

    }

    private var options1Items = ArrayList<JsonBean>()
    private val options2Items = ArrayList<ArrayList<String>>()
    private val options3Items = ArrayList<ArrayList<ArrayList<String>>>()

}