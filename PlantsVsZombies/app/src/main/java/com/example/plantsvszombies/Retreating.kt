package com.example.plantsvszombies

import android.graphics.Bitmap
import android.graphics.Matrix

class Retreating(override val context : Enemy) : MotionState {
    init {
        if (context.previousState is Advancing) {
            context.bitmap = mirrorBitmap(context.bitmap)
        }
    }

    override val velocity: Float
        get() = if (context.isFreezing) {
            -context.velocity / 30
        } else {
            -context.velocity
        }

    override fun updatePosition(gameElement: GameElement) {
        super.updatePosition(gameElement)
        if (getNearestBurgerDirection(context.burgerList) == true || getNearestBurgerDirection(
                context.burgerList
            ) == null
        ) {
            context.previousState = this
            context.changeMotionState(Advancing(context))
        } else {
            val target = context.getTargets(context.defenseList, TargetingMode.RIGHT).firstOrNull()
            if (target != null) {
                context.attack(target)
                context.previousState = this
                context.changeMotionState(Eating(context))
            }
        }
    }
}
