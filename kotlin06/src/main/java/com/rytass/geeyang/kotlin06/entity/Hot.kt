package com.rytass.geeyang.kotlin06.entity

/**
 * Created by yangjiru on 2018/1/8.
 */
data class Hot (
        val hot_num: String?,
        val bi: String?,
        val ti: String?,
        val ai: Int?,
        val author: String?,
        val title: String?,
        val board_name: String?,
        val desc: String?,
        val img_list: List<String>?,
        val url: String?
)
