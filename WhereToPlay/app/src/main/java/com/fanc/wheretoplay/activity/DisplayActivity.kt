package com.fanc.wheretoplay.activity

import android.os.Bundle
import android.support.v4.app.FragmentManager
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.base.BaseActivity
import com.fanc.wheretoplay.fragment.*

class DisplayActivity : BaseActivity() {



var mFragmentManager : FragmentManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
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





        val image = intent.getStringExtra("image")
        val book_sn = intent.getStringExtra("book_sn")
        val room_type = intent.getStringExtra("room_type")
        val total = intent.getStringExtra("total")
        val statues = intent.getStringExtra("statues")


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
                        .replace(R.id.displayfragment, BGirlApplyFragment1.newInstance())
                        .commit()
            }
            "BGirlApplyFragment2" ->{
                supportFragmentManager.beginTransaction()
                        .replace(R.id.displayfragment, BGirlApplyFragment2.newInstance())
                        .commit()
            }

            "BGirlApplyFragment3" ->{
                supportFragmentManager.beginTransaction()
                        .replace(R.id.displayfragment, BGirlApplyFragment3.newInstance())
                        .commit()
            }
            "BGirlApplyFragment4" ->{
                supportFragmentManager.beginTransaction()
                        .replace(R.id.displayfragment, BGirlApplyFragment4.newInstance())
                        .commit()
            }

            "BGirlApplyFragment5" ->{
                supportFragmentManager.beginTransaction()
                        .replace(R.id.displayfragment, BGirlApplyFragment5.newInstance())
                        .commit()
            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        mFragmentManager?.popBackStack()

    }
}
