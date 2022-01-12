package gordon.lab.mybouncingball.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color.red
import android.util.AttributeSet
import android.util.Log
import android.view.*
import android.view.View.MeasureSpec
import android.view.ViewGroup.MarginLayoutParams
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.GestureDetectorCompat
import gordon.lab.mybouncingball.R
import gordon.lab.mybouncingball.dpToPx

class BouncingBallGroup:ViewGroup  {

    private var mHLine: View? = null
    private var mVLine: View? = null

    private var mBall1: BouncingBall? = null
    private var mBall2: BouncingBall? = null
    private var mBall3: BouncingBall? = null
    private var mBall4: BouncingBall? = null

    constructor(context: Context?) : super(context){
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init(context)
    }

    private fun init(context: Context?) {

        context?.let {
            // Top Line
            mHLine = View(it)
            mHLine?.id = View.generateViewId()
            mHLine?.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 1.dpToPx())
            mHLine?.setBackgroundColor(ContextCompat.getColor(it, R.color.black))
            addView(mHLine)

            mVLine = View(it)
            mVLine?.id = View.generateViewId()
            mVLine?.layoutParams = LayoutParams(1.dpToPx(),LayoutParams.MATCH_PARENT)
            mVLine?.setBackgroundColor(ContextCompat.getColor(it, R.color.black))
            addView(mVLine)


            mBall1 = BouncingBall(it).apply {
                id = View.generateViewId()
                setBackgroundColor(ContextCompat.getColor(it, R.color.purple_200))
                layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
                addView(this)
            }

            mBall2 = BouncingBall(it).apply {
                id = View.generateViewId()
                layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
                setBackgroundColor(ContextCompat.getColor(it, R.color.teal_200))
                addView(this)
            }

            mBall3 = BouncingBall(it).apply {
                id = View.generateViewId()
                layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
                setBackgroundColor(ContextCompat.getColor(it, R.color.purple_700))
                addView(this)
            }

            mBall4 = BouncingBall(it).apply {
                id = View.generateViewId()
                layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
                setBackgroundColor(ContextCompat.getColor(it, R.color.teal_700))
                addView(this)
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // 獲得它的父容器為它設置的測量模式和大小
        val sizeWidth = MeasureSpec.getSize(widthMeasureSpec)
        val modeWidth = MeasureSpec.getMode(widthMeasureSpec)
        val sizeHeight = MeasureSpec.getSize(heightMeasureSpec)
        val modeHeight = MeasureSpec.getMode(heightMeasureSpec)


        // horizontal Line
        mHLine!!.measure(
            MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
            MeasureSpec.makeMeasureSpec(mHLine!!.layoutParams.height, MeasureSpec.AT_MOST)
        )

        // vertical Line
        mVLine!!.measure(
            MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
            MeasureSpec.makeMeasureSpec(mVLine!!.layoutParams.height, MeasureSpec.AT_MOST)
        )

        // 左上角的球
        mBall1!!.measure(
            MeasureSpec.makeMeasureSpec(width/2, MeasureSpec.AT_MOST),
            MeasureSpec.makeMeasureSpec(height/2, MeasureSpec.AT_MOST)
        )

        // 右上角的球
        mBall2!!.measure(
            MeasureSpec.makeMeasureSpec(width/2, MeasureSpec.AT_MOST),
            MeasureSpec.makeMeasureSpec(height/2, MeasureSpec.AT_MOST)
        )

        // 左下角的球
        mBall3!!.measure(
            MeasureSpec.makeMeasureSpec(width/2, MeasureSpec.AT_MOST),
            MeasureSpec.makeMeasureSpec(height/2, MeasureSpec.AT_MOST)
        )

        // 右下角的球
        mBall4!!.measure(
            MeasureSpec.makeMeasureSpec(width/2, MeasureSpec.AT_MOST),
            MeasureSpec.makeMeasureSpec(height/2, MeasureSpec.AT_MOST)
        )


        // 獲得子view的個數
        val cCount = childCount

        for (i in 0 until cCount) {
            val child: View = getChildAt(i)
             measureChild(child, widthMeasureSpec, heightMeasureSpec)
        }

        setMeasuredDimension(
            if (modeWidth == MeasureSpec.EXACTLY) sizeWidth else width + paddingLeft + paddingRight,
            if (modeHeight == MeasureSpec.EXACTLY) sizeHeight else height + paddingTop + paddingBottom
        )
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {

        val currPosX = paddingLeft
        val currPosY = paddingTop
        val height = bottom - top

        mHLine?.layout(currPosX, height/2, currPosX + measuredWidth - paddingRight - paddingLeft, height/2 + mHLine!!.measuredHeight)
        mVLine?.layout(width/2, currPosY, width/2 + mVLine!!.measuredWidth,  mVLine!!.measuredHeight)

        mBall1?.layout(currPosX, currPosY, (currPosX + mBall1!!.measuredWidth)/2 ,  height/2)
        mBall2?.layout(width/2, currPosY, (width/2 + mBall1!!.measuredWidth/2) ,  height/2)
        mBall3?.layout(currPosX, height/2, (currPosX + mBall1!!.measuredWidth)/2 ,  height)
        mBall4?.layout(width/2, height/2, (width/2 + mBall1!!.measuredWidth/2) ,  height)

    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        val currPosX = paddingLeft
        val currPosY = paddingTop

        mHLine?.layout(currPosX, e.y.toInt(), currPosX + measuredWidth - paddingRight - paddingLeft, e.y.toInt() + mHLine!!.measuredHeight)
        mVLine?.layout(e.x.toInt(), currPosY, e.x.toInt() + mVLine!!.measuredWidth,  mVLine!!.measuredHeight)

        mBall1?.layout(currPosX, currPosY, e.x.toInt() ,  e.y.toInt())
        mBall2?.layout(e.x.toInt(), currPosY, width ,   e.y.toInt())
        mBall3?.layout(currPosX, e.y.toInt(), e.x.toInt() , height)
        mBall4?.layout(e.x.toInt(), e.y.toInt(), width ,  height)

        return super.onTouchEvent(e)
    }
}