package com.fanc.wheretoplay.fragment

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import com.alipay.sdk.app.PayTask
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.DisplayActivity
import com.fanc.wheretoplay.activity.EvaluationSuccessActivity
import com.fanc.wheretoplay.base.BaseFragment
import com.fanc.wheretoplay.datamodel.AccessOrderIdModel
import com.fanc.wheretoplay.datamodel.OrderPayoffModel
import com.fanc.wheretoplay.pay.AliPayResult
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils
import com.fanc.wheretoplay.rx.RxBus
import com.fanc.wheretoplay.util.Constants
import com.fanc.wheretoplay.util.SPUtils
import com.fanc.wheretoplay.util.ToastUtils
import com.tencent.mm.sdk.modelpay.PayReq
import com.tencent.mm.sdk.openapi.IWXAPI
import com.tencent.mm.sdk.openapi.WXAPIFactory
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.bgirlapplyfragment4.*
import kotlinx.android.synthetic.main.bgirltitle.*
import okhttp3.MultipartBody
import kotlin.properties.Delegates

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
            }

            R.id.rb_alipay -> {
                payValue = "1"
            }
            else -> {
            }
        }
    }

     var wxApi: IWXAPI by Delegates.notNull()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(inflater.context, R.layout.bgirlapplyfragment4, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerBroadcastReceiver()
        wxApi = WXAPIFactory.createWXAPI(mContext, Constants.WX_APP_ID)
        wxApi.registerApp(Constants.WX_APP_ID)

        tbv.setTv_title("支付")
        tv4.setTextColor(Color.parseColor("#c4483c"))
        tv_money.text = "￥ ${arguments?.getString("bGirlPayMoney")}"


        val bgirltype = arguments?.getString("bgirltype")


        var observable: Observable<OrderPayoffModel>? = null

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


            observable?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe(object : Observer<OrderPayoffModel> {
                        override fun onComplete() = Unit

                        override fun onSubscribe(d: Disposable) = Unit

                        override fun onNext(t: OrderPayoffModel) {
                            if (t.code == "0") {

                                when (payValue) {


                                    "1" -> {
                                        aliPay(t.content.orderString)
                                    }
                                    "2" -> {
                                        wxPay(t.content)
                                    }
                                    "3" -> {
                                    }

                                    else -> {
                                    }
                                }
                            } else {
                                Toast.makeText(mContext, t.message, Toast.LENGTH_SHORT).show()

                            }
                        }

                        override fun onError(e: Throwable) {
                            Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show()
                        }

                    })


        }

        rg.setOnCheckedChangeListener(this)

    }

    private fun wxPay(json: OrderPayoffModel.ContentBean) {
        val req = PayReq()
        req.appId = json.appid
        req.partnerId = json.partnerid
        req.prepayId = json.prepayid
        req.nonceStr = json.noncestr
        req.timeStamp = json.timestamp
        req.packageValue = json.packageX
        req.sign = json.sign

        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        wxApi.sendReq(req)


    }

    private fun aliPay(orderString: String) {
        Observable.just(orderString).map {
            val alipay = PayTask(mContext)
            val result = alipay.payV2(orderString, true)
            AliPayResult(result)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<AliPayResult> {
                    override fun onSubscribe(disposable: Disposable) {

                    }

                    override fun onNext(payResult: AliPayResult) {
                        /**
                         * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                         */
                        val resultInfo = payResult.result// 同步返回需要验证的信息
                        val resultStatus = payResult.resultStatus
                        // 判断resultStatus 为9000则代表支付成功
                        if (TextUtils.equals(resultStatus, "9000")) {
                            // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                            ToastUtils.showShortToast(mContext, "支付成功")
                            // checkAliPayResult(resultInfo, orderId, discountId);
                            paySuccess()

                        } else {
                            // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                            ToastUtils.showShortToast(mContext, "支付失败")
                        }
                    }

                    override fun onError(throwable: Throwable) {

                    }

                    override fun onComplete() {

                    }
                })

    }


    var receiver: Receiver? = null
    private fun registerBroadcastReceiver() {
        receiver = Receiver()
        val filter = IntentFilter(Constants.ACTION_CHOOSE_DISCOUNT_COUPON)
        filter.addAction(Constants.ACTION_WXPAY_SUCCESS)
        LocalBroadcastManager.getInstance(mContext).registerReceiver(receiver!!, filter)
    }


    class Receiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent?.getAction()
            if (Constants.ACTION_WXPAY_SUCCESS == action) {
                Toast.makeText(context, "支付成功", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, DisplayActivity::class.java)
                intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment5")
                context?.startActivity(intent)
                val activity = context as DisplayActivity
                activity.finish()
            }
        }

    }

    // 支付成功去评价
    public fun paySuccess() {
        // 去评价


        Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show()
        val intent = Intent(mContext, DisplayActivity::class.java)
        intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment5")
        startActivity(intent)
        mContext.finish()
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


    override fun onDestroy() {
        super.onDestroy()
        if (receiver != null) {
            LocalBroadcastManager.getInstance(mContext).unregisterReceiver(receiver!!)
            receiver = null
        }
    }
}