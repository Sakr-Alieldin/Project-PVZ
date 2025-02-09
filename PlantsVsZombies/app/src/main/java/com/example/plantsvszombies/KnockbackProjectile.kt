package com.example.plantsvszombies

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class KnockbackProjectile(context: Context, gameControl: GameControl, override var x:Float, override val y:Float, override val attackDamage : Int) : Projectile(gameControl, context){
    override val sizeInPx = context.resources.displayMetrics.widthPixels / 15
    override var bitmap: Bitmap = resizeBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.knockbackpea))
    private fun knockback(target: Entity?) {
        if (target is Enemy) {
            target.x += 100
        }
    }
    override fun attack(target: Entity?) {
        super.attack(target)
        knockback(target)
    }
}