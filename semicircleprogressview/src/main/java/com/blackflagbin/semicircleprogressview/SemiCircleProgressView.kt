package com.blackflagbin.semicircleprogressview

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator

/**
 * Created by blackflagbin on 2017/11/30.
 */
open class SemiCircleProgressView : View, IProgressView {
    private var mProgress: Int = 0
    //默认宽度 200dp
    private var mDefaultWidth = dp2px(200f)
    //默认高度 100dp
    private var mDefaultHeight = dp2px(100f)
    //默认进度短线间隔角度 1度
    private var mAngle = 1
    //默认进度短线宽度 1dp
    private var mLineStrokeWidth = 1
    private var mShowProgressText = true
    private var mCenterX = 0f
    private var mCenterY = 0f
    //圆环宽度
    private var mRingWidth = 0f
    private var mLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mRingPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mCirclePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mProgressTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mPath = Path()
    private var mRed = 255
    private var mBlue = 255
    private var mGreen = 255
    private var mHexRedString = ""
    private var mHexBlueString = ""
    private var mHexGreenString = ""
    private var mWidth = mDefaultWidth
    private var mHeight = mDefaultHeight
    override fun setProgress(progress: Int) {
        mProgress = progress
        invalidate()
    }


    override fun getProgress(): Int {
        return mProgress
    }

    /**
     *  更新进度
     */
    fun updateProgress(
            progress: Int, duration: Long = Math.abs((progress - mProgress).toInt()) * 30L) {
        val animator = ObjectAnimator.ofInt(this, "progress", progress).setDuration(duration)
        animator.interpolator=AnticipateOvershootInterpolator()
        animator.start()
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {

        val attrSet = context.obtainStyledAttributes(
                attrs, R.styleable.SemiCircleProgressView)
        with(attrSet) {
            mProgress = attrSet.getInt(
                    R.styleable.SemiCircleProgressView_progress, 0)
            mAngle = attrSet.getInt(
                    R.styleable.SemiCircleProgressView_angle, 0)
            mLineStrokeWidth = attrSet.getInt(
                    R.styleable.SemiCircleProgressView_strokeWidth, 0)
            mShowProgressText = attrSet.getBoolean(
                    R.styleable.SemiCircleProgressView_showProgressText, true)
        }

        mLinePaint.color = Color.BLACK
        mLinePaint.strokeWidth = dp2px(mLineStrokeWidth.toFloat())
        mRingPaint.color = Color.parseColor("#F9F9F9")
        mRingPaint.style = Paint.Style.FILL
        mPath.fillType = Path.FillType.WINDING
        mCirclePaint.color = Color.parseColor("#0E000000")
        mCirclePaint.setShadowLayer(dp2px(4f), 0f, 0f, Color.parseColor("#F9F9F9"))
        mCirclePaint.style = Paint.Style.STROKE
        mCirclePaint.strokeWidth = dp2px(1f)
        mProgressTextPaint.textAlign = Paint.Align.CENTER
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        var width = measureHandler(widthMeasureSpec, mDefaultWidth.toInt())
        var height = measureHandler(heightMeasureSpec, mDefaultHeight.toInt())

        when {
            widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY -> {
                if (width > height * 2) {
                    width = height * 2
                } else {
                    height = width / 2
                }
            }

            widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY -> {
                height = width / 2
            }

            widthMode != MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY -> {
                width = height * 2
            }

        }

        if (width > height * 2) {
            width = height * 2
        } else {
            height = width / 2
        }

        mWidth = width.toFloat()
        mHeight = height.toFloat()

        mCenterX = width / 2f
        mCenterY = height.toFloat()
        mRingWidth = width / 10f
        mProgressTextPaint.textSize = (width / 7).toFloat()
        setMeasuredDimension(
                MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY))

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save()
        canvas.translate(mCenterX, mCenterY)

        mPath.addCircle(0f, 0f, mRingWidth * 4, Path.Direction.CW)
        mPath.addCircle(0f, 0f, mRingWidth * 5, Path.Direction.CCW)
        canvas.drawPath(mPath, mRingPaint)

        canvas.drawCircle(0f, 0f, mRingWidth * 4 + dp2px(3f), mCirclePaint)
        canvas.drawCircle(0f, 0f, mRingWidth * 5 - dp2px(3f), mCirclePaint)

        mRed = 250
        mGreen = 63
        mBlue = 63
        /*
            #FA3F3F rgb(250,63,63)
            #618AFF rgb(97,138,255)
            #61B4FF rgb(97,180,255)
            #3EE89A rgb(62,232,154)
         */
        var count = 180 / mAngle / 3
        for (i in 1 until (mProgress / 100f * 180 / mAngle).toInt()) {
            canvas.save()
            canvas.rotate(i * mAngle.toFloat(), 0f, 0f)

            when (i) {
                in 0 until count -> {
                    mRed -= 153 / count
                    mGreen += 75 / count
                    mBlue += 192 / count
                    if (mRed < 97) {
                        mRed = 97
                    }
                    if (mGreen > 138) {
                        mGreen = 138
                    }
                    if (mBlue > 255) {
                        mBlue = 255
                    }
                }

                in count until count * 2 -> {
                    mGreen += 42 / count
                    if (mGreen > 180) {
                        mGreen = 180
                    }

                }

                in count * 2 until count * 3 -> {
                    mRed -= 35 / count
                    mGreen += 52 / count
                    mBlue -= 101 / count
                    if (mRed < 62) {
                        mRed = 62
                    }
                    if (mGreen > 232) {
                        mGreen = 232
                    }
                    if (mBlue < 154) {
                        mBlue = 154
                    }
                }

            }

            mHexRedString = Integer.toHexString(mRed)
            if (mHexRedString.length < 2) {
                mHexRedString = "0$mHexRedString"
            }
            mHexBlueString = Integer.toHexString(mBlue)
            if (mHexBlueString.length < 2) {
                mHexBlueString = "0$mHexBlueString"
            }
            mHexGreenString = Integer.toHexString(mGreen)
            if (mHexGreenString.length < 2) {
                mHexGreenString = "0$mHexGreenString"
            }

            mLinePaint.color = Color.parseColor("#ff$mHexRedString$mHexGreenString$mHexBlueString")
            canvas.drawLine(
                    -mRingWidth * 4 - dp2px(6f), 0f, -mRingWidth * 5 + dp2px(6f), 0f, mLinePaint)
            canvas.restore()

        }

        if (mShowProgressText) {
            drawProgressText(canvas, mHeight)
        }

        canvas.restore()
    }

    open fun drawProgressText(canvas: Canvas, height: Float): Unit {
        canvas.drawText("$mProgress%", 0f, -dp2px(5f), mProgressTextPaint)
    }

    private fun dp2px(dpValue: Float): Float {
        val scale = resources.displayMetrics.density
        return (dpValue * scale + 0.5f)
    }

    private fun px2dp(pxValue: Float): Float {
        val scale = resources.displayMetrics.density
        return (pxValue / scale + 0.5f)
    }

    private fun measureHandler(measureSpec: Int, defaultSize: Int): Int {
        var result = defaultSize
        val specMode = View.MeasureSpec.getMode(measureSpec)
        val specSize = View.MeasureSpec.getSize(measureSpec)
        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize
        } else if (specMode == View.MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, specSize)
        } else {
            result = defaultSize
        }
        return result
    }


}
