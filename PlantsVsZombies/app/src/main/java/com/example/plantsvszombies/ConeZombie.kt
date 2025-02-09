package com.example.plantsvszombies
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory


class ConeZombie (gameControl : GameControl,
                    override val context: Context, override var x: Float, y: Float) : Enemy(gameControl,y){
    override val sizeInPx: Int = context.resources.displayMetrics.heightPixels / 10
    override var bitmap: Bitmap = resizeBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.conezombie))
    override var HP: Int = 300
    override val attackDamage = 5
    override val range = context.resources.displayMetrics.heightPixels / 10
    override val velocity = -1f
    companion object {
        const val generationProbability = 30
    }

}