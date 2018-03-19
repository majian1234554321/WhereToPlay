package com.fanc.wheretoplay.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

/**
 *
 * @author admin
 * @date 2018/3/16
 */
class BGirlApplyFragment2 : BaseFragment(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv1 -> {

            }
            R.id.iv2 -> {

            }
            R.id.iv3 -> {

            }
            else -> {
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(inflater.context, R.layout.bgirlapplyfragment2, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                    !TextUtils.isEmpty(eev15.data)) {


                val list = arrayListOf<MultipartBody.Part>()
                list.add(MultipartBody.Part.createFormData("id", eev1.data))
                list.add(MultipartBody.Part.createFormData("id", eev2.data))
                list.add(MultipartBody.Part.createFormData("id", eev3.data))
                list.add(MultipartBody.Part.createFormData("id", eev4.data))
                list.add(MultipartBody.Part.createFormData("id", eev5.data))
                list.add(MultipartBody.Part.createFormData("id", eev6.data))
                list.add(MultipartBody.Part.createFormData("id", eev7.data))
                list.add(MultipartBody.Part.createFormData("id", eev8.data))
                list.add(MultipartBody.Part.createFormData("id", eev9.data))
                list.add(MultipartBody.Part.createFormData("id", eev10.data))
                list.add(MultipartBody.Part.createFormData("id", eev11.data))
                list.add(MultipartBody.Part.createFormData("id", eev12.data))
                list.add(MultipartBody.Part.createFormData("id", eev13.data))
                list.add(MultipartBody.Part.createFormData("id", eev14.data))
                list.add(MultipartBody.Part.createFormData("id", eev15.data))
                list.add(MultipartBody.Part.createFormData("id", eev16.data))



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
}