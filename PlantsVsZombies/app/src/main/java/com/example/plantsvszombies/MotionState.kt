package com.example.plantsvszombies

import android.graphics.Bitmap
import android.graphics.Matrix
import kotlin.math.abs

interface MotionState : Move {
    val context: Enemy

    fun mirrorBitmap(inputBitmap: Bitmap): Bitmap {
        val matrix = Matrix()
        matrix.preScale(-1.0f, 1.0f)
        return Bitmap.createBitmap(inputBitmap, 0, 0, inputBitmap.width, inputBitmap.height, matrix, true)
    }
    fun getNearestBurgerDirection(burgerList : MutableSet<BurgerDefense>) : Boolean? {
        var nearestBurger : BurgerDefense? = null
        var minDistance = Float.MAX_VALUE
        val iterator = burgerList.iterator()
        while (iterator.hasNext()) {
            val burger = iterator.next()
            val distance = abs((context.x - burger.x))
            if (burger.isDead() || distance > burger.range) {
                iterator.remove()
            } else if (distance < minDistance) {
                minDistance = distance
                nearestBurger = burger
            }
        }
        return nearestBurger?.let { it.x < context.x } //true = left, false = right
    }

    override fun updatePosition(gameElement: GameElement) {
        super.updatePosition(gameElement)
        if(context.isBurning){
            context.changeHP(1)
        }
    }

}