package com.fanc.wheretoplay.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.adapter.BGirlYearFragmentAdapter
import com.fanc.wheretoplay.base.BaseFragment
import com.fanc.wheretoplay.datamodel.Modelsss
import com.fanc.wheretoplay.rx.Retrofit_RequestUtils
import com.fanc.wheretoplay.util.SPUtils
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.bgirlyearfragment.*
import kotlinx.android.synthetic.main.bgirlyearfragmentadapter.*
import okhttp3.MultipartBody


/**
 *
 * @author admin
 * @date 2018/3/15
 */
class BGirlYearFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(inflater.context, R.layout.bgirlyearfragment, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tbv.setTv_title(if (arguments?.getString("bgirltype") == "emplYearReviewStatus") "年审列表" else "补卡列表")

        ivssss.visibility = View.INVISIBLE
        recycle.layoutManager = LinearLayoutManager(mContext)


        Retrofit_RequestUtils.getRequest().emplDetailArray(MultipartBody.Part.createFormData("token", SPUtils(context).getUser().getToken()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Modelsss> {
                    override fun onComplete() = Unit

                    override fun onSubscribe(p0: Disposable) = Unit

                    override fun onNext(p0: Modelsss) {
                        if (p0.code == "0") {

                            if (recycle != null) {
                                recycle.adapter = BGirlYearFragmentAdapter(mContext, p0.content.contents, arguments?.getString("bgirltype"))
                            }

                        } else {

                        }
                    }

                    override fun onError(p0: Throwable) {
                        Toast.makeText(mContext, p0.toString(), Toast.LENGTH_SHORT).show()
                    }

                })


    }

    companion object {
        fun newInstance(bgirltype: String): BGirlYearFragment {
            val bgirlyearfragment = BGirlYearFragment()
            val args = Bundle()

            args.putString("bgirltype", bgirltype)

            bgirlyearfragment.arguments = args

            return bgirlyearfragment
        }
    }
}