package com.rytass.geeyang.kotlin04

import android.content.Context
import android.util.AttributeSet

/**
 * Created by yangjiru on 2018/1/5.
 */

class TouchableImageView: android.support.v7.widget.AppCompatImageView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


}
