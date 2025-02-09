package com.example.plantsvszombies

import kotlin.math.abs

enum class TargetingMode {
    LEFT, RIGHT, AOE
}

interface TargetGetter {
    val direction: TargetingMode
    val range: Int
    val x: Float
    val y: Float

    fun getTargets(entityList: MutableList<out Entity>, targetingMode: TargetingMode = direction): List<Entity> {
        val targets = mutableListOf<Entity>()
        for (entity in entityList) {
            val distanceX = when (targetingMode) {
                TargetingMode.LEFT -> (x - entity.x).toInt()
                TargetingMode.RIGHT -> (entity.x - x).toInt()
                TargetingMode.AOE -> abs((x - entity.x)).toInt() //attaque de zone
            }
            val distanceY = (y - entity.y).toInt()
            if (distanceX in (0..range) && distanceY in (-100..100)) {
                targets.add(entity)
                if (targetingMode != TargetingMode.AOE) {
                    break
                }
            }
        }
        return targets
    }

}
