package com.rytass.geeyang.kotlin06.holder

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.rytass.geeyang.kotlin06.R

/**
 * Created by yangjiru on 2018/1/8.
 */


@SuppressLint("WrongViewCast")
class HotHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    val cover = itemView?.findViewById<ImageView>(R.id.cover)
    val title = itemView?.findViewById<TextView>(R.id.title)
    val desc = itemView?.findViewById<TextView>(R.id.desc)
}