package com.example.plantsvszombies
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas


abstract class  GameElement{
    protected abstract val gameControl: GameControl
    protected abstract val context : Context //GameControl.context pourrait marcher si on met GameControl dans le constructeur (non abstract)
    abstract var x: Float
    abstract val y: Float
    abstract var bitmap: Bitmap
    protected abstract val sizeInPx: Int
    fun resizeBitmap(bitmap : Bitmap) : Bitmap{
        return Bitmap.createScaledBitmap(bitmap, sizeInPx, bitmap.height*sizeInPx/bitmap.width, false)
    }
    fun draw(canvas: Canvas){
        canvas.drawBitmap(bitmap, x, y, null)
    }
    protected abstract fun onDeath()

    open  fun nextFrame(){
        if(isDead()){
            onDeath()
        }
    }

    open fun isDead(): Boolean{
        return (x > 2090)
    }
}