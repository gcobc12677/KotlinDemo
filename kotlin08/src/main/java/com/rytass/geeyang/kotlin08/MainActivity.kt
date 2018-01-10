package com.rytass.geeyang.kotlin08

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.rytass.geeyang.kotlin08.custom.NavBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
    }

    private fun setListeners() {
        navBar.onItemClickListener = object: NavBar.OnItemClickListener {
            override fun onItemClick(position: Int, view: View) {
                Toast.makeText(this@MainActivity, "position: $position", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
