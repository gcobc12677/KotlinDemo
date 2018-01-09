package com.rytass.geeyang.kotlin08.custom

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.rytass.geeyang.kotlin08.R

/**
 * Created by yangjiru on 2018/1/9.
 */

class NavBar : LinearLayout {
    private val items = listOf(
            NavItem("home", R.drawable.home_icon),
            NavItem("dashboard", R.drawable.dashboard_icon),
            NavItem("notifications", R.drawable.notifications_icon)
    )
    private var btns = mutableListOf<NavBtn>()

    var index = 0

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, -1)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        orientation = HORIZONTAL
        items.forEachIndexed{ index, item ->
            val navItem = NavBtn(context)
            navItem.item = item
            navItem.tag = index
            navItem.setOnClickListener { view ->
                this@NavBar.index = view!!.tag as Int
                updateUI()
            }
            if (index == 0) navItem.isSelected = true
            addView(navItem)
            btns.add(navItem)
        }
    }

    private fun updateUI() {
        btns.forEachIndexed { index, navBtn ->
            when (index) {
                this@NavBar.index -> {
                    navBtn.isSelected = true
                }
                else -> {
                    navBtn.isSelected = false
                }
            }
        }
    }
}

class NavBtn : LinearLayout {
    var icon: ImageView
    var name: TextView

    var item: NavItem? = null
    set(value) {
        field = value

        if (value != null) {
            icon.setImageResource(value.icon)
            name.text = value.name
        }
    }

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, -1)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        inflate(context, R.layout.nav_bar_item, this)
        icon = findViewById(R.id.icon)
        name = findViewById(R.id.name)

        val lp = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT)
        lp.weight = 1f

        layoutParams = lp
        setBackgroundResource(R.drawable.nav_btn_bg)
        isClickable = true
        gravity = Gravity.CENTER
        orientation = VERTICAL
        setPadding(
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, resources.displayMetrics).toInt(),
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, resources.displayMetrics).toInt(),
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, resources.displayMetrics).toInt(),
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, resources.displayMetrics).toInt()
        )
    }
}

data class NavItem(val name: String, val icon: Int)
