package com.rytass.geeyang.kotlin04

import android.content.Context
import android.graphics.PointF
import android.graphics.Rect
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent

/**
 * Created by yangjiru on 2018/1/5.
 */

class TouchableImageView : android.support.v7.widget.AppCompatImageView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    enum class STATE {
        NORMAL,
        DRAG,
        ZOOM,
    }

    var state = STATE.NORMAL
        set(value) {
            println("state from $field to $value")
            field = value
        }

    var prePoint: PointF? = null
    var preSnapshot: Rect? = null

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val pointCount = event?.pointerCount

        when (event?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                println("ACTION_DOWN")
                val x0 = event?.getX(0)
                val y0 = event?.getY(0)
                prePoint = PointF(x0, y0)
                println("x0:${x0} y0:${y0}")

//                preSnapshot = Rect()
//                getWindowVisibleDisplayFrame(preSnapshot)
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                println("ACTION_POINTER_DOWN")
                when (pointCount) {
                    2 -> {
                        val x1 = event?.getX(1)
                        val y1 = event?.getY(1)
                        println("x1:${x1} y1:${y1}")
                    }
                }
            }
            MotionEvent.ACTION_MOVE -> {
                println("ACTION_MOVE")
                when (pointCount) {
                    1 -> {
                        val x0 = event?.getX(0)
                        val y0 = event?.getY(0)
                        println("x0:${x0} y0:${y0}")

//                        println(
//                                "${preSnapshot!!.left} + $x0 - ${prePoint!!.x}" + "\n" +
//                                "${preSnapshot!!.top} + $y0 - ${prePoint!!.y}" + "\n" +
//                                "${preSnapshot!!.right} + $x0 - ${prePoint!!.x}" + "\n" +
//                                "${preSnapshot!!.bottom} + $y0 - ${prePoint!!.y}"
//                        )
//                        setFrame(
//                                (preSnapshot!!.left + x0 - prePoint!!.x).toInt(),
//                                (preSnapshot!!.top + y0 - prePoint!!.y).toInt(),
//                                (preSnapshot!!.right + x0 - prePoint!!.x).toInt(),
//                                (preSnapshot!!.bottom + y0 - prePoint!!.y).toInt()
//                        )

                        state = STATE.DRAG
                    }
                    2 -> {
                        state = STATE.ZOOM
                    }
                }
            }
            MotionEvent.ACTION_POINTER_UP -> {
                println("ACTION_POINTER_UP")
            }
            MotionEvent.ACTION_UP -> {
                println("ACTION_UP")

                state = STATE.NORMAL
            }
        }

        gestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    val gestureDetector: GestureDetector = GestureDetector(context, GestureDetectorListener())

    class GestureDetectorListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDoubleTap(e: MotionEvent?): Boolean {
            println("onDoubleTap")
            return super.onDoubleTap(e)
        }
    }
}
