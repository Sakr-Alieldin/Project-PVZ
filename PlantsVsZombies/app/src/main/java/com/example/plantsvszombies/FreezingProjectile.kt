package com.example.plantsvszombies

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class FreezingProjectile(context: Context, gameControl: GameControl, override var x:Float, override val y:Float, override val attackDamage : Int) : Projectile(gameControl, context){
    override var bitmap: Bitmap = resizeBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.iceball))
    private fun freeze(target: Entity?) {
        if (target is Enemy) {
            target.isFreezing = true
        }
    }
    override fun attack(target: Entity?) {
        super.attack(target)
        freeze(target)
        }
    }
