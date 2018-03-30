package com.fanc.wheretoplay.activity

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.WindowManager
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.base.BaseActivity
import com.fanc.wheretoplay.fragment.*

class DisplayActivity : BaseActivity() {



var mFragmentManager : FragmentManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        val displayType = intent.getStringExtra("DISPLAYTYPE")
        val idValue = intent.getStringExtra("idValue")
        val storeIdValue = intent.getStringExtra("storeIdValue")

        val discountValue = intent.getStringExtra("discountValue")
        val address = intent.getStringExtra("address")
        val storeName = intent.getStringExtra("storeName")
        val phonevalue = intent.getStringExtra("phonevalue")

        val order_idValue = intent.getStringExtra("order_id")


        val dateValue = intent.getStringExtra("date")
        val descValue = intent.getStringExtra("desc")

        val weekValue = intent.getStringExtra("weekType")

        val order_functionValue = intent.getStringExtra("order_function")

        val application_id = intent.getStringExtra("application_id")


        val bGirlPayMoney =  intent.getStringExtra("bGirlPayMoney")


        val image = intent.getStringExtra("image")
        val book_sn = intent.getStringExtra("book_sn")
        val room_type = intent.getStringExtra("room_type")
        val total = intent.getStringExtra("total")
        val statues = intent.getStringExtra("statues")


        val bgirltype = intent.getStringExtra("bgirltype")


        mFragmentManager = supportFragmentManager










        when (displayType) {
            "CouponFragment" -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.displayfragment, CouponFragment())
                        .commit()
            }
            "PackageFragment" -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.displayfragment, PackageFragment(idValue, storeIdValue, discountValue, storeName, address, phonevalue))
                        .commit()
            }

            "PackageDetailsFragment" -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.displayfragment, PackageDetailsFragment(order_idValue, order_functionValue))
                        .commit()
            }

            "PackageKTVFragment" -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.displayfragment, PackageKTVFragment(idValue, storeIdValue, discountValue, storeName, address, phonevalue,
                                dateValue, descValue, weekValue))
                        .commit()
            }
            "CancelOrderFragment" ->{
                supportFragmentManager.beginTransaction()
                        .replace(R.id.displayfragment, CancelOrderFragment.newInstance(order_idValue,storeName,image,book_sn,room_type
                        ,total,statues))
                        .commit()
            }

            "BGirlFragment" ->{
                supportFragmentManager.beginTransaction()
                        .replace(R.id.displayfragment, BGirlFragment.newInstance())
                        .commit()
            }

            "BGirlApplyFragment1" ->{
                supportFragmentManager.beginTransaction()
                        .replace(R.id.displayfragment, BGirlApplyFragment1.newInstance(bgirltype))
                        .commit()
            }
            "BGirlApplyFragment2" ->{
                supportFragmentManager.beginTransaction()
                        .replace(R.id.displayfragment, BGirlApplyFragment2.newInstance(bgirltype))
                        .commit()
            }

            "BGirlApplyFragment2_1" ->{
                supportFragmentManager.beginTransaction()
                        .replace(R.id.displayfragment, BGirlApplyFragment2_1.newInstance(bgirltype,application_id))
                        .commit()
            }

            "BGirlApplyFragment3" ->{
                supportFragmentManager.beginTransaction()
                        .replace(R.id.displayfragment, BGirlApplyFragment3.newInstance(statues,bgirltype,application_id))
                        .commit()
            }
            "BGirlApplyFragment4" ->{
                supportFragmentManager.beginTransaction()
                        .replace(R.id.displayfragment, BGirlApplyFragment4.newInstance(bGirlPayMoney,bgirltype,application_id))
                        .commit()
            }

            "BGirlApplyFragment5" ->{
                supportFragmentManager.beginTransaction()
                        .replace(R.id.displayfragment, BGirlApplyFragment5.newInstance())
                        .commit()
            }



            "BGirlYearFragment" ->{
                supportFragmentManager.beginTransaction()
                        .replace(R.id.displayfragment, BGirlYearFragment.newInstance(bgirltype))
                        .commit()
            }



        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        mFragmentManager?.popBackStack()

    }
}
