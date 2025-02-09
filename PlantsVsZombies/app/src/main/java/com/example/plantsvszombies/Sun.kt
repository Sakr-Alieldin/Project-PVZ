
package com.example.plantsvszombies

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory



@SuppressLint("ViewConstructor")
class Sun (context : Context, override var posX: Float, override var posY: Float, private val gameControl: GameControl, natural: Boolean): Resource(context){
    override val sizeInPx = context.resources.displayMetrics.widthPixels / 10
    override var bitmap = resizeBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.sun))
    private val speedY = if (natural) 8 else 0
    private var speedX = 0

    fun setSpeedX(speed: Int) {
        speedX = speed
    }

    override fun handleTouch(touchX: Float, touchY: Float): Boolean{
        //println("Sun touched")
        return if (isTouched(touchX, touchY)) {
            //println("touch handled")
            gameControl.deadResource.add(this)
            gameControl.addSun()
            true
        } else {
            // If the touch event is not within the Sun button, don't consume it
            false
        }
    }

    override fun nextFrame() {
        posY += speedY
        posX += speedX
        this.invalidate()
        if (posY > 2200 || posX > 2200 || posY < -300 || posX < 0) {
            gameControl.deadResource.add(this)
        }

    }

}