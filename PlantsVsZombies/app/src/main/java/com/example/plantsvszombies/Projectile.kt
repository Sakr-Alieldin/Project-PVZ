package com.example.plantsvszombies

import android.content.Context


abstract class Projectile (override val gameControl: GameControl, override val context: Context) : GameElement(), Attack, TargetGetter,Move {
    override val sizeInPx = context.resources.displayMetrics.widthPixels / 25
    override val range = 50
    override val velocity = 10f //Vitesse en px qui varie en fonction de l'écran
    override val direction = TargetingMode.RIGHT

    override fun isDead(): Boolean {
        val zombie = getTargets(gameControl.enemyList).firstOrNull()
        if (zombie != null) {
            attack(zombie)
            return true
        }
        return (super.isDead())
    }

    override fun onDeath() {
        gameControl.deadProjectiles.add(this)
    }
    override fun nextFrame() {
        super.nextFrame()
        updatePosition(this)
    }

}
