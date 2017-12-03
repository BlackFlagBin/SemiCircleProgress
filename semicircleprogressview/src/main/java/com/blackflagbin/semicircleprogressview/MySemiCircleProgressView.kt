package com.blackflagbin.semicircleprogressview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet

/**
 * Created by blackflagbin on 2017/12/3.
 */
class MySemiCircleProgressView : SemiCircleProgressView {
    private var mDeviceState = true
    private val mTopPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mProgressPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mBottomPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    fun setDeviceState(state: Boolean) {
        mDeviceState = state
        invalidate()
    }

    init {
        mTopPaint.textAlign = Paint.Align.CENTER
        mTopPaint.color = Color.parseColor("#313131")
        mProgressPaint.textAlign = Paint.Align.CENTER
        mProgressPaint.color = Color.parseColor("#313131")
        mProgressPaint.isFakeBoldText = true
        mBottomPaint.textAlign = Paint.Align.CENTER
        mBottomPaint.color = Color.parseColor("#313131")
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun drawProgressText(canvas: Canvas, height: Float) {
        mBottomPaint.textSize = 1 / 12f * height

        if (mDeviceState) {
            mBottomPaint.color = Color.parseColor("#313131")
            canvas.drawText("设备在线", 0f, -1 / 8f * height, mBottomPaint)
        } else {
            mBottomPaint.color = Color.parseColor("#F0464C")
            canvas.drawText("设备离线", 0f, -1 / 8f * height, mBottomPaint)
        }

        mProgressPaint.textSize = 1 / 5f * height
        if (getProgress() <= 25) {
            mProgressPaint.color = Color.parseColor("#F0464C")
        } else {
            mProgressPaint.color = Color.parseColor("#313131")
        }
        canvas.drawText(
                "${getProgress()}%", 0f,
                -1 / 8f * height - mBottomPaint.fontSpacing - 1 / 10f * height, mProgressPaint)

        mTopPaint.textSize = 1 / 10f * height
        canvas.drawText(
                "电量", 0f,
                -1 / 8f * height - mBottomPaint.fontSpacing - 1 / 10f * height - mProgressPaint.fontSpacing - 1 / 18f * height,
                mTopPaint)
    }
}