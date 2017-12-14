package com.fanc.wheretoplay.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.fanc.wheretoplay.R
import com.fanc.wheretoplay.base.BaseActivity
import com.fanc.wheretoplay.fragment.CouponFragment
import kotlinx.android.synthetic.main.activity_display.view.*

class DisplayActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)
        val displayType = intent.getStringExtra("DISPLAYTYPE")

        when (displayType) {
            "CouponFragment" -> {
                supportFragmentManager.beginTransaction().replace(R.id.displayfragment, CouponFragment()).commit()
            }
            "" -> {
            }
        }
    }
}
