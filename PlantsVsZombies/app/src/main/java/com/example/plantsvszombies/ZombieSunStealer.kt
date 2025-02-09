package com.example.plantsvszombies

import android.content.Context
import android.graphics.BitmapFactory

class ZombieSunStealer (gameControl: GameControl,override val context: Context,override var x: Float, y: Float): Enemy(gameControl,y) {
    override val sizeInPx: Int = context.resources.displayMetrics.heightPixels / 7
    override var HP: Int = 100
    override val attackDamage = 5
    override val range = 100
    override var bitmap = resizeBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.sunstealer))
    private var tickCounter = 0
    override val velocity = -1f
    companion object {
        //const val generationProbability = 10 Non utilisé mais utile si d'autres ennemis
    }

    private val resourceList: MutableList<Resource> = gameControl.resourceList

    private fun stealSun() {
        for (resource in resourceList) {
            if (resource is Sun) {
                resource.setSpeedX(20)
                resource.bitmap = resource.resizeBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.redsun))
                }
            }
    }
    override fun nextFrame() {
        super.nextFrame()
        tickCounter++
        if (tickCounter >= 50) {
            stealSun()
            tickCounter = 0
        }
    }
}

