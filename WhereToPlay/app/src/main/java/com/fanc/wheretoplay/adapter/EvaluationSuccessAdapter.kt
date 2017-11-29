package com.fanc.wheretoplay.adapter

import android.support.v7.widget.RecyclerView
import android.text.SpannableString
import android.text.Spanned
import android.text.style.TextAppearanceSpan
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.util.Util
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.datamodel.StoreDetailModel
import com.fanc.wheretoplay.network.Network

import kotlinx.android.synthetic.main.evaluationsuccessadapter.view.*


/**
 * Created by admin on 2017/11/16.
 */

class EvaluationSuccessAdapter(var list: MutableList<StoreDetailModel.ContentBean.StoreBean.ListBean>) : RecyclerView.Adapter<EvaluationSuccessAdapter.ViewHolder>() {



    override fun getItemCount(): Int = if (list.size > 0) list.size else 0

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder =
            ViewHolder(View.inflate(parent?.context, R.layout.evaluationsuccessadapter, null))

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        with(holder?.itemView!!) {
            tv_reserve_item_title.text = list[position].name
            if (list[position].discount.isNotEmpty()) {
                val text = SpannableString(list[position].discount + "折")
                text.setSpan(TextAppearanceSpan(context, R.style.reserve_dicount), 0, text.length - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                text.setSpan(TextAppearanceSpan(context, R.style.reserve_dicount_small), text.length - 1, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                tv_reserve_item_discount_sum.setText(text, TextView.BufferType.SPANNABLE)
                tv_reserve_item_discount_sum.visibility = View.VISIBLE
            } else {
                tv_reserve_item_discount_sum.visibility = View.GONE
            }

            Glide.with(context).load(list[position].cover).placeholder(R.drawable.default_rect).into(iv_reserve_item)
            tv_reserve_item_address.text = list[position].area
            tv_reserve_item_price.text = list[position].capita




        }




    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}
