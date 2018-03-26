package com.fanc.wheretoplay.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.Display
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.DisplayActivity
import com.fanc.wheretoplay.activity.SignInActivity
import com.fanc.wheretoplay.datamodel.MerchantDetailModel

import com.fanc.wheretoplay.datamodel.MerchantDetailModel.ContentBean.StoreBean.PackageBean.PkgListBean
import com.fanc.wheretoplay.util.Constants
import com.fanc.wheretoplay.util.SPUtils
import kotlinx.android.synthetic.main.extendadapter.view.*

/**
 * @author admin
 * @date 2017/12/24
 */

class ExtendAdapter(val context: Context, var flag: Boolean,
                    val list: MutableList<MerchantDetailModel.ContentBean.StoreBean.PackageBean.PkgListBean>,
                    val storeid: String?
                    , val discountValue: String?, val storeName: String?, val address: String?, val phonevalue: String?
) : RecyclerView.Adapter<ExtendAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(View.inflate(context, R.layout.extendadapter, null))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView) {
            Glide.with(context).load(list[position].pic).placeholder(R.drawable.default_rect).into(iv)
            tv1text.text = list[position].name
            tv2.text = "￥ ${list[position].discount_price}"
            tv3.text = "￥" + list[position].origin_price

            tv3.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG

            setOnClickListener {
                val intent = Intent()
                if (!SPUtils(context).getBoolean(Constants.IS_SIGN_IN, false)) {
                    intent.setClass(context, SignInActivity::class.java)
                } else {
                    intent.setClass(context, DisplayActivity::class.java)
                    intent.putExtra("DISPLAYTYPE", "PackageFragment")
                    intent.putExtra("idValue", list[position].id)
                    intent.putExtra("storeIdValue", storeid)

                    intent.putExtra("discountValue", discountValue)
                    intent.putExtra("storeName", storeName)
                    intent.putExtra("address", address)
                    intent.putExtra("phonevalue", phonevalue)
                }

                context.startActivity(intent)

            }

        }
    }

    override fun getItemCount(): Int {
        return if (flag && list.size > 2) {
            list.size
        } else if (list.size > 2) {
            2
        } else {
            list.size
        }
    }


    fun change(flag: Boolean) {
        this.flag = flag
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)


}
