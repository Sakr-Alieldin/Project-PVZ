package com.example.plantsvszombies
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlin.random.Random


class NormalPlant(override val square : BoardButton, override val context: Context, override var x: Float, override val y: Float, override val gameControl: GameControl) : Shooter() {

    companion object {
        const val PRICE: Int = 3
    }

    override val sizeInPx: Int = context.resources.displayMetrics.widthPixels / 15
    override var bitmap: Bitmap = resizeBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.peashooter))
    override val attackDamage: Int = 10
    override val shootingDelay: Int = 500
    override var HP: Int = 2000


    override fun shootProjectile(context: Context) {
        val projectile = NormalProjectile(context, gameControl, x + 100, y, this.attackDamage)
        gameControl.newProjectileList.add(projectile)
    }

    override fun boostedShootProjectile(context: Context) {
        val projectile = NormalProjectile(context, gameControl, x + 100 + Random.nextInt(1, 51), y +Random.nextInt(1, 61) , this.attackDamage )
        gameControl.newProjectileList.add(projectile)
    }

}