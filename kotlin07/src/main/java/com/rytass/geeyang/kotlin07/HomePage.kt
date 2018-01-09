package com.rytass.geeyang.kotlin07

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.home_page.*

/**
 * Created by yangjiru on 2018/1/9.
 */

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        setListeners()
    }

    private fun setListeners() {
        button.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, MapsActivity::class.java)
            startActivity(intent)
        }
    }
}