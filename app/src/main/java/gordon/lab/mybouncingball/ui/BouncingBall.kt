package gordon.lab.mybouncingball.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View

class BouncingBall:View {

    private lateinit var ball:Ball

    constructor(context: Context?) :super(context) {
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init()
    }

    private fun init() {
        ball = Ball(100, Color.RED)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let{
            ball.move(canvas)

            canvas.drawOval(ball.getBallOval(), ball.getBallPaint())

            invalidate()
        }
    }
}