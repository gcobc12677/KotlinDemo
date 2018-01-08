package com.rytass.geeyang.kotlin06

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.rytass.geeyang.kotlin06.adapter.HotAdapter
import com.rytass.geeyang.kotlin06.api.FetchHots
import com.rytass.geeyang.kotlin06.entity.Hot
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var hotAdapter: HotAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initial()
    }

    private fun initial() {
        layoutManager = LinearLayoutManager(this)
        list.layoutManager = layoutManager

        hotAdapter = HotAdapter(this)
        list.adapter = hotAdapter

        FetchHots(this).execute()
    }

    fun setData(hots: Set<Hot>) {
        hotAdapter?.hots = hots
        hotAdapter?.notifyDataSetChanged()
    }
}
