package com.fanc.wheretoplay.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.ListOrderActivity
import com.fanc.wheretoplay.base.BaseFragment
import com.fanc.wheretoplay.image.GlideImageLoader
import com.fanc.wheretoplay.presenter.CancelOrderPresent
import com.fanc.wheretoplay.rx.RxBus
import com.fanc.wheretoplay.view.CancelOrderView
import com.fanc.wheretoplay.view.TitleBarView
import kotlinx.android.synthetic.main.cancelorderfragment.*

/**
 *
 * @author admin
 * @date 2018/2/23
 */
class CancelOrderFragment : BaseFragment(), CancelOrderView {
    override fun success() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Toast.makeText(mContext, "申请成功", Toast.LENGTH_SHORT).show()

        val intent = Intent()
        intent.putExtra("Key", "Value")
        RxBus.getDefault().post(intent)
        mContext.finish()
    }

    override fun failed(value: String) {
        Toast.makeText(mContext, "申请失败：$value", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(order_id: String, storeName: String, image: String): CancelOrderFragment {
            val fragment = CancelOrderFragment()
            val bundle = Bundle()
            bundle.putString("order_id", order_id)
            bundle.putString("storeName", storeName)
            bundle.putString("image", image)

            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(inflater.context, R.layout.cancelorderfragment, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tbv.setTv_title("申请取消")
        tbv.setTv_right("提交")


        val bundle = arguments
        val orderId = bundle?.getString("order_id")
        tv1.text = bundle?.getString("storeName")
        tv2.text = "订单类型：${bundle?.getString("storeName")}"
        tv3.text = bundle?.getString("storeName")
        tv4.text = bundle?.getString("storeName")

        GlideImageLoader.display(context, iv, bundle?.getString("image"))
        tbv.setOnRightClickListener({
            CancelOrderPresent(mContext, this).cancelOrder(orderId, et_reason.text.toString())
        })


    }
}