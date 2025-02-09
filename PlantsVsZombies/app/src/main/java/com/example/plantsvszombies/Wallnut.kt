package com.example.plantsvszombies

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory


class Wallnut(override val gameControl: GameControl, override val square: BoardButton, override val context : Context, override var x: Float, override val y: Float) : Defense(){
    companion object {
        const val PRICE: Int = 5
    }
    override var HP = 10000
    override var sizeInPx: Int =context.resources.displayMetrics.widthPixels / 12
    override var bitmap: Bitmap = resizeBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.musculardoge))
    override fun getBoosted() {
        HP = 20000

    }
    override fun nextFrame() {
        if ((HP > 10000) && sizeInPx != context.resources.displayMetrics.widthPixels / 9) {
            sizeInPx = context.resources.displayMetrics.widthPixels / 9
            bitmap = resizeBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.musculardoge))
        }
        else if ((HP >= 5000) && (HP <= 10000) && sizeInPx != context.resources.displayMetrics.widthPixels / 12){
            sizeInPx = context.resources.displayMetrics.widthPixels / 12
            bitmap = resizeBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.musculardoge))
        }
        else if ((HP < 5000) && sizeInPx != context.resources.displayMetrics.widthPixels / 15) {
            sizeInPx = context.resources.displayMetrics.widthPixels / 15
            bitmap = resizeBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.cryingdoge))
        }
        if (this.isDead()) {
            onDeath()
        }
    }

}