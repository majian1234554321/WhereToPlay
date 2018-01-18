package com.fanc.wheretoplay.activity

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.text.TextUtils
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import com.alipay.sdk.app.PayTask
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.base.ApiException
import com.fanc.wheretoplay.base.BaseActivity
import com.fanc.wheretoplay.datamodel.AccessOrderIdModel
import com.fanc.wheretoplay.datamodel.MessageDetail
import com.fanc.wheretoplay.datamodel.OrderPayoffModel
import com.fanc.wheretoplay.fragment.PayPayFragment
import com.fanc.wheretoplay.network.Network
import com.fanc.wheretoplay.pay.AliPayResult
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils
import com.fanc.wheretoplay.rx.RxBus
import com.fanc.wheretoplay.util.Constants
import com.fanc.wheretoplay.util.ToastUtils
import com.orhanobut.logger.Logger
import com.tencent.mm.sdk.modelpay.PayReq
import com.tencent.mm.sdk.openapi.IWXAPI
import com.tencent.mm.sdk.openapi.WXAPIFactory
import com.unionpay.UPPayAssistEx
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_paypay.*
import okhttp3.Call
import okhttp3.MultipartBody
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

class PayPayActivity : BaseActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    var payWay = 2
    var storeIdValue: String? = ""
    var packageIdValue: String? = ""
    var orderId: String? = ""

    lateinit var value4: String
    lateinit var wxApi: IWXAPI
    private val mMode = "00"

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        when (p1) {
            R.id.rb_wxpay -> {
                rb_wxpay.isChecked = true
                payWay = 2
            }
            R.id.rb_alipay -> {
                rb_alipay.isChecked = true
                payWay = 1
            }
            R.id.rb_yupay -> {
                rb_yupay.isChecked = true
                payWay = 3

            }
            R.id.rb_upppay -> {
                rb_upppay.isChecked = true
                payWay = 4
            }
        }

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
            val alipay = PayTask(this)
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

    override fun onClick(p0: View?) {

        when (p0?.id) {
            R.id.btn_pay -> {

                when (payWay) {
                    3 -> {
                        alertBalancePay()
                    }
                    else -> {
                        showProgress()
                        if (TextUtils.isEmpty(orderId)) {
                            payMoney("")
                        } else {
                            payOrder("")
                        }

                    }
                }

            }

        }
    }

    //根orderId直接支付
    private fun payOrder(password: String) {
        val list2 = ArrayList<MultipartBody.Part>()
        list2.add(MultipartBody.Part.createFormData("token", mUser.token))
        list2.add(MultipartBody.Part.createFormData("order_id", orderId))
        list2.add(MultipartBody.Part.createFormData("money", value4))
        list2.add(MultipartBody.Part.createFormData("type", payWay.toString()))
        list2.add(MultipartBody.Part.createFormData("code", password))


        val observable = if (typeValue == "优惠预订") {
            Retrofit_RequestUtils
                    .getRequest()
                    .discountBookPayoff(list2)

        } else {
            Retrofit_RequestUtils
                    .getRequest()
                    .setMealOrderPayoff(list2)
        }

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<OrderPayoffModel> {
                    override fun onSubscribe(disposable: Disposable) {

                    }

                    override fun onNext(orderPayoffModel: OrderPayoffModel) {
                        closeProgress()
                        when (payWay) {
                            1 -> {
                                aliPay(orderPayoffModel.content.orderString)
                            }
                            2 -> {
                                wxPay(orderPayoffModel.content)
                            }

                            3 -> {
                                if ("0" == orderPayoffModel.code) {
                                    paySuccess()
                                } else {
                                    Toast.makeText(this@PayPayActivity, orderPayoffModel.code.plus(orderPayoffModel.message).plus("------->" + orderId), Toast.LENGTH_SHORT).show()
                                }
                            }

                            4 -> {
                                UPPayAssistEx.startPay(this@PayPayActivity, null, null, orderPayoffModel.content.orderString, mMode)
                            }
                        }


                    }

                    override fun onError(throwable: Throwable) {
                        closeProgress()
                        Toast.makeText(this@PayPayActivity, throwable.toString(), Toast.LENGTH_LONG).show()
                    }

                    override fun onComplete() {

                    }
                })


    }

    fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    //根据获取orderId后支付
    private fun payMoney(password: String) {
        //支付流程  根据StoreId 金额  获取到订单id才能支付

        val list = arrayListOf<MultipartBody.Part>()


        list.add(MultipartBody.Part.createFormData("store_id", storeIdValue))
        list.add(MultipartBody.Part.createFormData("token", mUser.token))
        list.add(MultipartBody.Part.createFormData("package_id", if (TextUtils.isEmpty(packageIdValue)) "" else packageIdValue))

        list.add(MultipartBody.Part.createFormData("book_package_detail_id", if (TextUtils.isEmpty(packageIdValue)) "" else packageIdValue))
        list.add(MultipartBody.Part.createFormData("book_start_time", if (TextUtils.isEmpty(book_start_time)) "" else book_start_time))
        list.add(MultipartBody.Part.createFormData("discount_book_hour", if (TextUtils.isEmpty(discount_book_hour)) "" else discount_book_hour))
        list.add(MultipartBody.Part.createFormData("book_week_day", if (TextUtils.isEmpty(book_week_day)) "" else book_week_day))

        val observable = if (typeValue == "优惠预订") {
            Retrofit_RequestUtils
                    .getRequest()
                    .discountBookImmediatelyPay(list)

        } else {
            Retrofit_RequestUtils
                    .getRequest()
                    .setMealImmediatelyPay(list)
        }


        /* observable.subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(object :Observer<AccessOrderIdModel>{
                     override fun onNext(p0: AccessOrderIdModel) {
                         //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                         Toast.makeText(this@PayPayActivity, p0.message, Toast.LENGTH_LONG).show()
                     }

                     override fun onComplete() {
                         //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                     }

                     override fun onError(p0: Throwable) {
                         //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                     }

                     override fun onSubscribe(p0: Disposable) {
                         //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                     }

                 })*/


        /****************************/
        observable
                .subscribeOn(Schedulers.io())
                .flatMap { accessOrderIdModel ->

                    if (accessOrderIdModel.code != "0") {
                        toast(accessOrderIdModel.message)

                        Observable.error<OrderPayoffModel>(ApiException(accessOrderIdModel.message))

                    } else {
                        val list2 = ArrayList<MultipartBody.Part>()
                        list2.add(MultipartBody.Part.createFormData("token", mUser.token))
                        list2.add(MultipartBody.Part.createFormData("order_id", accessOrderIdModel.content.order_id))
                        list2.add(MultipartBody.Part.createFormData("money", value4))
                        list2.add(MultipartBody.Part.createFormData("type", payWay.toString()))
                        list2.add(MultipartBody.Part.createFormData("code", password))
                        orderId = accessOrderIdModel.content.order_id
                        if (typeValue == "优惠预订") {
                            Retrofit_RequestUtils.getRequest().discountBookPayoff(list2)
                        } else {
                            Retrofit_RequestUtils.getRequest().setMealOrderPayoff(list2)
                        }
                    }

                }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<OrderPayoffModel> {
                    override fun onSubscribe(disposable: Disposable) {

                    }

                    override fun onNext(orderPayoffModel: OrderPayoffModel) {
                        closeProgress()
                        when (payWay) {
                            1 -> {
                                aliPay(orderPayoffModel.content.orderString)
                            }
                            2 -> {
                                wxPay(orderPayoffModel.content)
                            }

                            3 -> {
                                if ("0" == orderPayoffModel.code) {
                                    paySuccess()
                                } else {
                                    Toast.makeText(this@PayPayActivity, orderPayoffModel.message.plus(orderId), Toast.LENGTH_SHORT).show()
                                }
                            }

                            4 -> {
                                UPPayAssistEx.startPay(this@PayPayActivity, null, null, orderPayoffModel.content.orderString, mMode)
                            }
                        }


                    }

                    override fun onError(throwable: Throwable) {
                        closeProgress()
                        Toast.makeText(this@PayPayActivity, throwable.toString(), Toast.LENGTH_LONG).show()
                    }

                    override fun onComplete() {

                    }
                })
        /**************************/

    }

    var discountValue: String? = ""
    var address: String? = ""
    var storeName: String? = ""
    var typeValue: String? = ""

    var book_package_detail_id: String? = ""
    var book_week_day: String? = ""
    var discount_book_hour: String? = ""

    var book_start_time: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paypay)

        wxApi = WXAPIFactory.createWXAPI(mContext, Constants.WX_APP_ID)
        wxApi.registerApp(Constants.WX_APP_ID)


        typeValue = intent.getStringExtra("type")
        if ("优惠预订" == typeValue) {
            tbv.setTv_title("优惠预订支付")
        } else {
            tbv.setTv_title("套餐支付")
        }

        storeIdValue = intent.getStringExtra("storeIdValue")
        packageIdValue = intent.getStringExtra("packageIdValue")

        discountValue = intent.getStringExtra("discountValue")
        address = intent.getStringExtra("address")
        storeName = intent.getStringExtra("storeName")


        orderId = intent.getStringExtra("order_id")

        val value0 = intent.getStringExtra("value0")
        val value1 = intent.getStringExtra("value1")
        val value2 = intent.getStringExtra("value2")
        val value3 = intent.getStringExtra("value3")
        value4 = intent.getStringExtra("value4")
        val value5 = intent.getStringExtra("value5")


        book_package_detail_id = intent.getStringExtra("book_package_detail_id")
        book_week_day = intent.getStringExtra("book_week_day")
        discount_book_hour = intent.getStringExtra("discount_book_hour")
        book_start_time = intent.getStringExtra("book_start_time")


        val fragment = PayPayFragment(storeName, value1, value2, value3, value4, value5)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_dispaly, fragment).commit()

        rg.setOnCheckedChangeListener(this)
        btn_pay.setOnClickListener(this)
    }

    /**
     * 余额支付时，检查是否设置过支付密码
     */
    private fun alertBalancePay() {
        if (!mSpUtils.getBoolean(Constants.IS_SET_PAY_PASSWORD, false)) {
            com.fanc.wheretoplay.view.AlertDialog(this)
                    .setTitle("提示")
                    .setContent("您还没有设置支付密码，现在去设置？")
                    .setBtnOnClickListener { _, _ ->
                        val intent = Intent(mContext, DetailActivity::class.java)
                        intent.putExtra(Constants.PAGE, Constants.SET_PAY_PWD)
                        startActivity(intent)
                    }
                    .setCanceledOnTouchOutside(true)
                    .show()
        } else {
            com.fanc.wheretoplay.view.AlertDialog(this)
                    .setPasswordInputBox()
                    .setBtnOnClickListener(com.fanc.wheretoplay.view.AlertDialog.OnBtnClickListener { _, input ->
                        if (input.isEmpty()) {
                            ToastUtils.makePicTextShortToast(mContext, "请输入支付密码")
                            return@OnBtnClickListener
                        }
                        if (input.length < 6) {
                            ToastUtils.makePicTextShortToast(mContext, "支付密码位数不正确")
                            return@OnBtnClickListener
                        }


                        if (TextUtils.isEmpty(orderId)) {
                            payMoney(input)
                        } else {
                            payOrder(input)
                        }

                    })
                    .setCanceledOnTouchOutside(true)
                    .show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        /*************************************************
         * 步骤3：处理银联手机支付控件返回的支付结果
         */
        if (data == null) {
            return
        }
        var msg = ""
        /*
         * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
         */
        val str = data.extras?.getString("pay_result")
        if ("success".equals(str, ignoreCase = true)) {

            // 如果想对结果数据验签，可使用下面这段代码，但建议不验签，直接去商户后台查询交易结果
            // result_data结构见c）result_data参数说明
            if (data.hasExtra("result_data")) {
                val result = data.extras.getString("result_data")
                try {
                    val resultJson = JSONObject(result)
                    val sign = resultJson.getString("sign")
                    val dataOrg = resultJson.getString("data")
                    // 此处的verify建议送去商户后台做验签
                    // 如要放在手机端验，则代码必须支持更新证书
                    val ret = verify(dataOrg, sign, mMode)
                    if (ret) {
                        // 验签成功，显示支付结果
                        msg = "支付成功！"
                    } else {
                        // 验签失败
                        msg = "支付失败！"
                    }
                } catch (e: JSONException) {
                }

            }
            // 结果result_data为成功时，去商户后台查询一下再展示成功
            msg = "支付成功！"
        } else if ("fail".equals(str, ignoreCase = true)) {
            msg = "支付失败！"
        } else if ("cancel".equals(str, ignoreCase = true)) {
            msg = "用户取消了支付"
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("支付结果通知")
        builder.setMessage(msg)
        builder.setInverseBackgroundForced(true)
        // builder.setCustomTitle();
        builder.setNegativeButton("确定") { dialog, which -> dialog.dismiss() }
        builder.create().show()
    }

    private fun verify(msg: String, sign64: String, mode: String): Boolean {
        return true

    }

    // 支付成功去评价
    private fun paySuccess() {
        // 去评价


        Toast.makeText(this@PayPayActivity, "支付成功", Toast.LENGTH_SHORT).show()
        val intent = Intent(mContext, EvaluationSuccessActivity::class.java)
        intent.putExtra(Constants.PAGE, Constants.TO_EVALUATE)
        intent.putExtra(Constants.STORE_ID, storeIdValue)
        intent.putExtra(Constants.ORDER_ID, orderId)
        intent.putExtra(Constants.PRICE, value4)
        intent.putExtra(Constants.IS_COMMENT, true)
        intent.putExtra("discount", discountValue)
        intent.putExtra("store_name", storeName)
        intent.putExtra("address", address)
        intent.putExtra("titleType", "支付成功")

        intent.putExtra("Key", "Value")
        RxBus.getDefault().post(intent)

        startActivity(intent)
        finish()
    }
}
