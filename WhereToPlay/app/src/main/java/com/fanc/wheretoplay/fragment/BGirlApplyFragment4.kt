package com.fanc.wheretoplay.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.DisplayActivity
import com.fanc.wheretoplay.base.BaseFragment
import com.fanc.wheretoplay.datamodel.AccessOrderIdModel
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils
import com.fanc.wheretoplay.util.SPUtils
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.bgirlapplyfragment4.*
import kotlinx.android.synthetic.main.bgirltitle.*
import okhttp3.MultipartBody

/**
 *
 * @author admin
 * @date 2018/3/16
 */
class BGirlApplyFragment4 : BaseFragment(), RadioGroup.OnCheckedChangeListener {

    var payValue = "1"

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.rb_wxpay -> {
                payValue = "2"
                Toast.makeText(mContext, "1", Toast.LENGTH_SHORT).show()
            }

            R.id.rb_alipay -> {
                payValue = "1"
                Toast.makeText(mContext, "2", Toast.LENGTH_SHORT).show()
            }
            else -> {
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(inflater.context, R.layout.bgirlapplyfragment4, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tbv.setTv_title("支付")
        tv4.setTextColor(Color.parseColor("#c4483c"))
        tv_money.text = arguments?.getString("bGirlPayMoney")


        val bgirltype = arguments?.getString("bgirltype")


        var observable: Observable<AccessOrderIdModel>? = null

        val listArgs = arrayListOf<MultipartBody.Part>()
        listArgs.add(MultipartBody.Part.createFormData("token", SPUtils(context).getUser().getToken()))
        listArgs.add(MultipartBody.Part.createFormData("pay_mode", payValue))


        when (bgirltype) {
            "emplApplicationStatus" -> {//申请

                listArgs.add(MultipartBody.Part.createFormData("application_amount", arguments?.getString("bGirlPayMoney")))
                observable = Retrofit_RequestUtils
                        .getRequest()
                        .emplApplicationPayOrder(listArgs)
            }
            "emplYearReviewStatus" -> {//年审
                listArgs.add(MultipartBody.Part.createFormData("year_review_amount", arguments?.getString("bGirlPayMoney")))

                observable = Retrofit_RequestUtils
                        .getRequest()
                        .emplYearReviewPayOrder(listArgs)
            }
            "emplPatchCardStatus" -> {//补卡
                listArgs.add(MultipartBody.Part.createFormData("patch_card_amount", arguments?.getString("bGirlPayMoney")))

                observable = Retrofit_RequestUtils
                        .getRequest()
                        .emplPatchCardPayOrder(listArgs)
            }


            else -> {
            }
        }




        next.setOnClickListener {


            when (payValue) {
                "1" -> {
                    alipay()
                }
                "2" -> {
                    wxPay()
                }
                "3" -> {

                }
                else -> {
                }
            }

        }

        rg.setOnCheckedChangeListener(this)

    }

    private fun wxPay() {

    }

    private fun alipay() {

    }

    companion object {
        fun newInstance(bGirlPayMoney: String, bgirltype: String): BGirlApplyFragment4 {

            val bGirlApplyFragment4 = BGirlApplyFragment4()

            val args = Bundle()
            args.putString("bGirlPayMoney", bGirlPayMoney)

            args.putString("bgirltype", bgirltype)


            bGirlApplyFragment4.arguments = args

            return bGirlApplyFragment4
        }
    }
}