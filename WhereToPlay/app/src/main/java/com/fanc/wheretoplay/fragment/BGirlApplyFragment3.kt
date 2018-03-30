package com.fanc.wheretoplay.fragment

import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.DisplayActivity
import com.fanc.wheretoplay.base.BaseFragment
import com.fanc.wheretoplay.datamodel.AccessOrderIdModel
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils
import com.fanc.wheretoplay.util.SPUtils
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.bgirlapplyfragment3.*
import kotlinx.android.synthetic.main.bgirltitle.*
import okhttp3.MultipartBody
import java.util.*

/**
 *
 * @author admin
 * @date 2018/3/16
 */
class BGirlApplyFragment3 : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(inflater.context, R.layout.bgirlapplyfragment3, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tbv.setTv_title("从业申请")
        tv3.setTextColor(Color.parseColor("#c4483c"))

        val bgirltype = arguments?.getString("bgirltype");

        val application_id = arguments?.getString("application_id");






        //0未申请1未审核2已审核3审核不通过
        when (arguments?.getString("statues")) {
            "1" -> {
                tv_text.text = "资料审核中，请耐心等待···"
                iv_status.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.iv_status1))
                next.visibility = View.GONE
            }
            "2" -> {
                tv_text.text = "恭喜，资料审核通过，请支付相应费用"
                iv_status.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.iv_status2))
                next.text = "支付"
                next.visibility = View.VISIBLE



                next.setOnClickListener {
                    Retrofit_RequestUtils.getRequest()
                            .emplGetAmount(MultipartBody.Part.createFormData("token", SPUtils(context).getUser().getToken()))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(object : Observer<AccessOrderIdModel> {
                                override fun onComplete() = Unit

                                override fun onSubscribe(d: Disposable) = Unit

                                override fun onNext(t: AccessOrderIdModel) {
                                    if (t.code == "0") {
                                        val intent = Intent(mContext, DisplayActivity::class.java)

                                        when (bgirltype) {
                                            "emplApplicationStatus" -> intent.putExtra("bGirlPayMoney", t.content.application_amount)
                                            "emplYearReviewStatus" -> intent.putExtra("bGirlPayMoney", t.content.year_review_amount)
                                            else -> intent.putExtra("bGirlPayMoney", t.content.patch_card_amount)
                                        }

                                        intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment4")
                                        intent.putExtra("bgirltype", bgirltype)
                                        intent.putExtra("application_id", application_id)


                                        startActivity(intent)
                                        mContext.finish()

                                    }

                                }

                                override fun onError(e: Throwable) {

                                }

                            })
                }



            }
            "3" -> {
                tv_text.text = "资料审核失败，请重新提交信息登记"
                iv_status.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.iv_status3))
                next.text = "登记"
                next.visibility = View.VISIBLE
                next.setOnClickListener {
                    val intent = Intent(mContext, DisplayActivity::class.java)


                    intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment2")

                    intent.putExtra("bgirltype", bgirltype)


                    startActivity(intent)
                    mContext.finish()
                }
            }
            else -> {
            }
        }


    }

    companion object {
        fun newInstance(status: String, bgirltype: String,application_id:String): BGirlApplyFragment3 {
            val bGirlApplyFragment3 = BGirlApplyFragment3()
            val args = Bundle()
            args.putString("statues", status)
            args.putString("bgirltype", bgirltype)
            args.putString("application_id", application_id)

            bGirlApplyFragment3.arguments = args

            return bGirlApplyFragment3
        }
    }
}