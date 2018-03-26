package com.fanc.wheretoplay.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.activity.DisplayActivity
import com.fanc.wheretoplay.base.BaseFragment
import com.fanc.wheretoplay.datamodel.AccessOrderIdModel
import com.fanc.wheretoplay.presenter.BGirlFragmentPresent
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils
import com.fanc.wheretoplay.util.SPUtils
import com.fanc.wheretoplay.util.ToastUtils
import com.fanc.wheretoplay.view.BGirlFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.bgirlfragment.*
import okhttp3.MultipartBody

/**
 *
 * @author admin
 * @date 2018/3/15
 */
class BGirlFragment : BaseFragment(), View.OnClickListener, BGirlFragmentView {
    override fun setSuccessData(type: String, content: AccessOrderIdModel.ContentBean) {

        when (type) {
            "emplApplicationStatus" -> {

                when (content.status) {
                    "0" -> {
                        val intent = Intent(mContext, DisplayActivity::class.java)
                        intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment1")
                        intent.putExtra("bgirltype", "emplApplicationStatus")
                        startActivity(intent)
                    }
                    else -> {
                        val intent = Intent(mContext, DisplayActivity::class.java)
                        intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment3")
                        intent.putExtra("statues", content.status)
                        intent.putExtra("bgirltype", "emplApplicationStatus")
                        startActivity(intent)
                    }
                }


            }
            "emplYearReviewStatus" -> {

                //0未年审1未审核2年审已审核3审核不通过
                when (content.year_review_status) {

                    "0" -> {
                        val intent = Intent(mContext, DisplayActivity::class.java)
                        intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment1")

                        intent.putExtra("bgirltype", "emplYearReviewStatus")

                        startActivity(intent)
                    }
                    else -> {
                        val intent = Intent(mContext, DisplayActivity::class.java)
                        intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment3")
                        intent.putExtra("bgirltype", "emplYearReviewStatus")
                        intent.putExtra("statues", content.year_review_status)
                        startActivity(intent)
                    }

                }

            }
            "emplPatchCardStatus" -> {
                //string 0未补卡1未审核2已审核3审核不通过
                when (content.patch_card_status) {

                    "0" -> {
                        val intent = Intent(mContext, DisplayActivity::class.java)
                        intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment1")
                        intent.putExtra("bgirltype", "emplPatchCardStatus")
                        startActivity(intent)
                    }
                    else -> {
                        val intent = Intent(mContext, DisplayActivity::class.java)
                        intent.putExtra("DISPLAYTYPE", "BGirlApplyFragment3")
                        intent.putExtra("bgirltype", "emplPatchCardStatus")
                        intent.putExtra("statues", content.patch_card_status)
                        startActivity(intent)
                    }

                }
            }
            else -> {
            }
        }


    }

    override fun setFailedData(value: String) {
        ToastUtils.showShortToast(mContext, value)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv1 -> {

                val bGirlFragmentPresent = BGirlFragmentPresent(mContext, this)
                bGirlFragmentPresent.emplApplicationStatus("emplApplicationStatus")

            }
            R.id.tv2 -> {
                val bGirlFragmentPresent = BGirlFragmentPresent(mContext, this)
                bGirlFragmentPresent.emplApplicationStatus("emplYearReviewStatus")
            }
            R.id.tv3 -> {
                val bGirlFragmentPresent = BGirlFragmentPresent(mContext, this)
                bGirlFragmentPresent.emplApplicationStatus("emplPatchCardStatus")
            }
            else -> {
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(inflater.context, R.layout.bgirlfragment, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tbv.setTv_title("娱乐行业从业申请")
        tv1.setOnClickListener(this)
        tv2.setOnClickListener(this)
        tv3.setOnClickListener(this)

    }

    companion object {
        fun newInstance(): BGirlFragment {
            val fragment = BGirlFragment()
            return fragment
        }
    }
}