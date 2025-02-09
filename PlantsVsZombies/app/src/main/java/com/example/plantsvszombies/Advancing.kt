package com.example.plantsvszombies

class Advancing (override val context: Enemy): MotionState{
    init {
        if(context.previousState is Retreating) {
            context.bitmap = mirrorBitmap(context.bitmap)
        }
    }

    override val velocity: Float
        get() = if (context.isFreezing) {
            context.velocity / 30
        } else {
            context.velocity
        }

    override fun updatePosition(gameElement : GameElement){
        super.updatePosition(gameElement)
        if(getNearestBurgerDirection(context.burgerList) == false){
            context.previousState = this
            context.changeMotionState(Retreating(context))
        } else {
            val target = context.getTargets(context.defenseList).firstOrNull()
            if(target != null) {
                    context.attack(target)
                    context.previousState = this
                    context.changeMotionState(Eating(context))
                }
            }
        }
    }
