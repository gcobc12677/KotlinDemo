package com.rytass.geeyang.kotlin06.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.rytass.geeyang.kotlin06.GlideApp
import com.rytass.geeyang.kotlin06.R
import com.rytass.geeyang.kotlin06.entity.Hot
import com.rytass.geeyang.kotlin06.holder.HotHolder

/**
 * Created by yangjiru on 2018/1/8.
 */

class HotAdapter(val activity: Activity) : RecyclerView.Adapter<HotHolder>() {
    var hots: Set<Hot>? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HotHolder {
        val view = activity.layoutInflater.inflate(R.layout.hot_item, null)

        return HotHolder(view)
    }

    override fun onBindViewHolder(holder: HotHolder, position: Int) {
        val hot = hots!!.elementAt(position)
        holder.title!!.text = hot.title
        holder.desc!!.text = hot.desc
        GlideApp.with(activity).load(if (hot.img_list?.isNotEmpty()!!) hot.img_list?.get(0) else null).into(holder.cover)
    }

    override fun getItemCount(): Int {
        if (hots != null) return hots!!.size
        return 0
    }
}
