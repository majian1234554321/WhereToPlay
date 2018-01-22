package com.fanc.wheretoplay.adapter

import android.content.Context
import android.content.Intent

import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.RecyclerView
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.TextAppearanceSpan
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.TextView

import com.bumptech.glide.Glide
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.DetailActivity
import com.fanc.wheretoplay.datamodel.MerchantDetailModel

import com.fanc.wheretoplay.util.Constants
import kotlinx.android.synthetic.main.item_reserve.view.*

import java.text.DecimalFormat


/**
 *
 * @author Administrator
 * @date 2017/6/13
 */

class MerchAdapter(val mContext: Context, val mData: List<MerchantDetailModel.ContentBean.StoreBean.ListBean>) : RecyclerView.Adapter<MerchAdapter.ViewHolder>() {

    internal var lastPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(View.inflate(mContext, R.layout.item_reserve2, null))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        startAnimation(holder.itemView, position)

        with(holder.itemView) {
            Glide.with(mContext).load(mData[position].cover).placeholder(R.drawable.default_rect).into(iv_reserve_item)
            if (mData[position].discount.isNotEmpty()) {
                val text = SpannableString(mData[position].discount + "折")
                text.setSpan(TextAppearanceSpan(mContext, R.style.reserve_dicount), 0, text.length - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                text.setSpan(TextAppearanceSpan(mContext, R.style.reserve_dicount_small), text.length - 1, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                tv_reserve_item_discount_sum.setText(text, TextView.BufferType.SPANNABLE)
                tv_reserve_item_discount_sum.visibility = View.VISIBLE

                tv_reserve_item_price.text = mData[position].capita

            } else {
                tv_reserve_item_discount_sum.visibility = View.GONE
            }






            if (mData[position].distance != null && !TextUtils.isEmpty(mData[position].distance)) {

                val distance = java.lang.Double.parseDouble(mData[position].distance)

                val d = if (distance < 500) {
                    "<500m"
                } else {
                    if (distance < 1000) {

                        mData[position].distance + "m"
                    } else {
                        // 0.几的时候，格式化会把小数点前的0去掉，原因未知
                        val df = DecimalFormat("#.0")
                        df.format(distance / 1000) + "km"
                    }

                }
                tv_reserve_item_address.text = mData[position].area + "   " + d
            }


            holder.itemView.setOnClickListener {
                val intent = Intent(mContext, DetailActivity::class.java)
                intent.putExtra(Constants.PAGE, Constants.MERCHANT_DETAIL)
                intent.putExtra(Constants.STORE_ID, mData[position].id)
                val compat = ActivityOptionsCompat.makeCustomAnimation(mContext, R.anim.anim_enter_bottom, R.anim.anim_out_top_right)
                //                mContext.startActivity(intent);
                ActivityCompat.startActivity(mContext, intent, compat.toBundle())
            }


        }


    }

    override fun getItemCount(): Int {
        return mData.size
    }

    private fun startAnimation(view: View, position: Int) {
        if (position != lastPosition) {
            val animationSet = AnimationUtils.loadAnimation(mContext, R.anim.anim_item_bottom_in) as AnimationSet
            animationSet.startTime = 20
            view.startAnimation(animationSet)
            lastPosition = position
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}
