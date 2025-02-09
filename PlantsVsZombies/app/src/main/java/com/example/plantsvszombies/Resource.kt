package com.example.plantsvszombies

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.RectF
import android.view.MotionEvent
import android.view.View


abstract class Resource(context: Context) : View(context), View.OnClickListener {
    abstract var posX: Float
    abstract val posY: Float
    abstract var bitmap: Bitmap
    abstract val sizeInPx: Int

    init {
        this.translationZ = 100f
    }

    override fun onClick(v: View?) {}

    override fun draw(canvas: Canvas){
        super.draw(canvas)
        canvas.drawBitmap(bitmap,posX, posY, null)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            return handleTouch(event.x, event.y)
        }
        return super.onTouchEvent(event)
    }

    abstract fun nextFrame()

    protected abstract fun handleTouch(touchX: Float, touchY: Float): Boolean

    fun resizeBitmap(bitmap : Bitmap) : Bitmap{
        return Bitmap.createScaledBitmap(bitmap, sizeInPx, bitmap.height*sizeInPx/bitmap.width, false)
    }

    protected fun isTouched(touchX: Float, touchY: Float): Boolean {
        val rect = RectF(posX, posY, posX + sizeInPx, posY + sizeInPx)
        return rect.contains(touchX, touchY)
    }


}