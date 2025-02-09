package com.example.plantsvszombies

class Eating (override val context: Enemy): MotionState{
    override val velocity = 0f

    override fun updatePosition(gameElement : GameElement){
        super.updatePosition(gameElement)
        val target = if(context.previousState is Advancing){
            context.getTargets(context.defenseList).firstOrNull()
        }
        else {
            context.getTargets(context.defenseList, TargetingMode.RIGHT).firstOrNull()
        }
        if(target == null){
            if(getNearestBurgerDirection(context.burgerList) == true || getNearestBurgerDirection(context.burgerList) == null){
                context.changeMotionState(Advancing(context))
            }
            else {
                context.changeMotionState(Retreating(context))
            }
        }
        else {
            context.attack(target)
        }
    }
}