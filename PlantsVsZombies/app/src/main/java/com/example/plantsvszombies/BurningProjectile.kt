package com.example.plantsvszombies

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class BurningProjectile(context: Context, gameControl: GameControl, override var x:Float, override val y:Float, override val attackDamage : Int) : Projectile(gameControl, context){
    override var bitmap: Bitmap = resizeBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.fireball1))
    private fun burn(target: Entity?){
        if (target is Enemy) {
            target.isBurning = true
        }
    }
    override fun attack(target: Entity?) {
        super.attack(target)
        burn(target)

    }
}