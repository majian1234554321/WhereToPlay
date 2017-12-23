package com.fanc.wheretoplay.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.datamodel.CouponListModel
import com.fanc.wheretoplay.util.DateFormatUtil
import kotlinx.android.synthetic.main.couponchildadapter.view.*


/**
 *
 * @author admin
 * @date 2017/12/23
 */
class CouponChildAdapter(val context: Context, val list: List<CouponListModel.ContentBean.ListBean>, val type: String) : RecyclerView.Adapter<CouponChildAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView) {
            tv_money.text = list[position].price
            tv_endtime.text = DateFormatUtil.stampToDate(list[position].end_time)
            if ("2" == type || "3" == type) {
                tv_money.setTextColor(Color.parseColor("#ffcccccc"))
                tv_item_discount_coupon_text.setTextColor(Color.parseColor("#ffcccccc"))
                tv_rmb.setTextColor(Color.parseColor("#ffcccccc"))
                tv_000.setTextColor(Color.parseColor("#ffcccccc"))
                tv_001.setTextColor(Color.parseColor("#ffcccccc"))
                rl00.setBackgroundResource(R.drawable.shape_discount_coupon_top2)
                rl01.setBackgroundResource(R.drawable.shape_discount_coupon_bottom2)
                iv001.visibility = View.VISIBLE
                if (type == "2") {
                    iv001.setBackgroundResource(R.drawable.coupon2)
                } else {
                    iv001.setBackgroundResource(R.drawable.coupon3)
                }

            } else {
                tv_money.setTextColor(Color.parseColor("#ffc4483c"))
                tv_item_discount_coupon_text.setTextColor(Color.parseColor("#ffc4483c"))
                tv_rmb.setTextColor(Color.parseColor("#ffc4483c"))
                tv_000.setTextColor(Color.parseColor("#ffc4483c"))
                tv_001.setTextColor(Color.parseColor("#ffc4483c"))
                rl00.setBackgroundResource(R.drawable.shape_discount_coupon_top)
                rl01.setBackgroundResource(R.drawable.shape_discount_coupon_bottom)
                iv001.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
            ViewHolder(View.inflate(context, R.layout.couponchildadapter, null))

    override fun getItemCount(): Int = if (list.isNotEmpty()) list.size else 0

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)


}