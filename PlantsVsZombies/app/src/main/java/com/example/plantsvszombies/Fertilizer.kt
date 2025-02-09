package com.example.plantsvszombies
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

@SuppressLint("ViewConstructor")
class Fertilizer(context : Context, override var posX: Float, override val posY: Float, private val gameControl: GameControl): Resource(context){
    override var bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.poop)
    override val sizeInPx =context.resources.displayMetrics.widthPixels / 10

    override fun nextFrame() {
        if (posY > 2200 || posX > 2200 || posY < -300 || posX < 0) {
            gameControl.deadResource.add(this)
        }
    }

    override fun handleTouch(touchX: Float, touchY: Float): Boolean{
        return if (isTouched(touchX, touchY)) {
            gameControl.deadResource.add(this)
            gameControl.addFertilizer()
            true
        } else {
            // If the touch event is not within the Fertilizer button, don't consume it
            false
        }
    }
}