package com.rytass.geeyang.kotlin06

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rytass.geeyang.kotlin06.api.FetchHots

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initial()
    }

    private fun initial() {
        FetchHots().execute()
    }
}
