package com.fanc.wheretoplay.adapter

import android.content.Context
import android.content.Intent

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.DisplayActivity
import com.fanc.wheretoplay.datamodel.AccessOrderIdModel
import com.fanc.wheretoplay.datamodel.Content2
import com.fanc.wheretoplay.presenter.BGirlFragmentPresent
import com.fanc.wheretoplay.view.BGirlFragmentView

import kotlinx.android.synthetic.main.bgirlyearfragmentadapter.view.*

/**
 *
 * @author admin
 * @date 2018/3/30
 */
class BGirlYearFragmentAdapter(val mContext: Context, val list: List<Content2>, val bgirltype: String?) : RecyclerView.Adapter<BGirlYearFragmentAdapter.ViewHolder>(), BGirlFragmentView {
    override fun setSuccessData(type: String, content: AccessOrderIdModel.ContentBean,applicationId:String) {
        when (type) {

            "emplYearReviewStatus" -> {

                //0未年审1未审核2年审已审核3审核不通过
                when (content.year_review_status) {

                    "0" -> {
                        val intent = Intent(mContext, DisplayActivity::class.java)

                        intent.putExtra("bgirltype", bgirltype)
                        intent.putExtra("application_id", applicationId)
                        intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment2_1")

                        mContext.startActivity(intent)

                    }

                    "4" -> {
                        Toast.makeText(mContext, "年审支付完成", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        val intent = Intent(mContext, DisplayActivity::class.java)
                        intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment3")
                        intent.putExtra("bgirltype", "emplYearReviewStatus")
                        intent.putExtra("statues", content.year_review_status)
                        intent.putExtra("application_id", applicationId)
                        mContext.startActivity(intent)
                    }

                }

            }
            "emplPatchCardStatus" -> {
                //string 0未补卡1未审核2已审核3审核不通过
                when (content.patch_card_status) {

                    "0" -> {
                        val intent = Intent(mContext, DisplayActivity::class.java)

                        intent.putExtra("bgirltype", bgirltype)
                        intent.putExtra("application_id", applicationId)
                        intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment2_1")

                        mContext.startActivity(intent)

                    }


                    "4" -> {
                        Toast.makeText(mContext, "补卡支付完成", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        val intent = Intent(mContext, DisplayActivity::class.java)
                        intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment3")
                        intent.putExtra("bgirltype", "emplPatchCardStatus")
                        intent.putExtra("statues", content.patch_card_status)
                        mContext.startActivity(intent)

                    }

                }
            }
            else -> {
            }
        }
    }

    override fun setFailedData(value: String) {
        Toast.makeText(mContext, value, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(View.inflate(mContext, R.layout.bgirlyearfragmentadapter, null))

    override fun getItemCount(): Int = if (list.isNotEmpty()) list.size else 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView) {
            tv1.text = list[position].nowAddress
            tv2.text = list[position].name
            tv3.text = list[position].mobile
            tv4.text = list[position].profession

            setOnClickListener {

                //判断状态
                val bGirlFragmentPresent = BGirlFragmentPresent(mContext, this@BGirlYearFragmentAdapter,list[position].applicationId)
                bGirlFragmentPresent.emplApplicationStatus(if ("emplYearReviewStatus"  ==bgirltype)"emplYearReviewStatus" else "emplPatchCardStatus")

            }
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}