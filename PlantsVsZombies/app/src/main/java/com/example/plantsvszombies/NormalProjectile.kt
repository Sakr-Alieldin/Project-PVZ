package com.example.plantsvszombies
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class NormalProjectile (context: Context, gameControl: GameControl, override var x:Float, override val y:Float, override val attackDamage : Int) : Projectile(gameControl, context){
    override var bitmap: Bitmap = resizeBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.regularprojectile))

}