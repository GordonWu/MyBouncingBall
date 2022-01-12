package gordon.lab.mybouncingball.ui

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.Log

class Ball(private var size:Int, color:Int) {

    private var direction = floatArrayOf(1f, 1f)
    private var paint: Paint = Paint()
    private lateinit var oval: RectF
    private var needInit = true
    private var x:Float = 0f
    private var y:Float = 0f
    private var lastWidth = 0
    private var lastHeight = 0
    init {
        paint.color = color
    }

    fun move(canvas: Canvas) {
        if (needInit){
            x =  (0..canvas.width).random().toFloat()
            y =  (0..canvas.height).random().toFloat()
            needInit = false
        }

        if (lastWidth == 0 && lastHeight == 0){
            // 初始化
            lastWidth = canvas.width
            lastHeight = canvas.height
        }

        x += 10 * direction[0]
        y += 10 * direction[1]

        oval = RectF(x - size / 2, y - size / 2, x + size / 2, y + size / 2)

         val bounds = Rect()
        oval.roundOut(bounds)

         if (!canvas.clipBounds.contains(bounds)) {
            if (lastWidth != canvas.width || lastHeight != canvas.height){
                // 視窗變動太小重新定位
                x = canvas.width.toFloat()-size
                y = canvas.height.toFloat()-size
            }else{
                if (x - size < 0 || x + size > canvas.width) {
                    Log.d("gw","碰撞牆壁(寬)")
                    direction[0] = direction[0] * -1
                }
                if (y - size < 0 || y + size > canvas.height) {
                    Log.d("gw","碰撞牆壁(高)")
                    direction[1] = direction[1] * -1
                }
            }
        }

        lastWidth = canvas.width
        lastHeight = canvas.height
    }

    fun getBallOval():RectF{
        return RectF(x - size / 2, y - size / 2, x + size / 2, y + size / 2)
    }

    fun getBallPaint():Paint{
        return paint
    }
}