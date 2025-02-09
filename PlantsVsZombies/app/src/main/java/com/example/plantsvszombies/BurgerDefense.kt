package com.example.plantsvszombies

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory



class BurgerDefense(override val gameControl: GameControl, override val square: BoardButton, override val context: Context, override var x : Float, override val y : Float) : Defense(), TargetGetter{
    companion object {
        const val PRICE: Int = 5
    }
    override val sizeInPx : Int = context.resources.displayMetrics.widthPixels / 12
    override var bitmap: Bitmap = resizeBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.burger))
    override var HP = 5000
    override val range = context.resources.displayMetrics.widthPixels / 5
    override val direction = TargetingMode.AOE
    override fun getBoosted() {
        this.HP = 10000
    }

    private fun attractZombie(zombie : Enemy){
        zombie.getAttracted(this)}


    override fun nextFrame() {
        super.nextFrame()
        for (target in getTargets(gameControl.enemyList)) {
            attractZombie(target as Enemy)
            println("Zombie attracted by burger")
        }
    }
}


