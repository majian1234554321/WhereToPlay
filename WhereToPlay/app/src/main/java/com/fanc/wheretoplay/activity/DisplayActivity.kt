package com.fanc.wheretoplay.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.base.BaseActivity
import com.fanc.wheretoplay.fragment.CouponFragment
import com.fanc.wheretoplay.fragment.PackageFragment
import kotlinx.android.synthetic.main.activity_display.view.*

class DisplayActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        val displayType = intent.getStringExtra("DISPLAYTYPE")
       val idValue =  intent.getStringExtra("idValue")

        when (displayType) {
            "CouponFragment" -> {
                supportFragmentManager.beginTransaction().replace(R.id.displayfragment, CouponFragment()).commit()
            }
            "PackageFragment" -> {
                supportFragmentManager.beginTransaction().replace(R.id.displayfragment, PackageFragment(idValue)).commit()
            }
        }
    }
}
