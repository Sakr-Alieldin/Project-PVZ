package com.example.plantsvszombies

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.util.Timer
import kotlin.concurrent.timerTask
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class Sunflower (override val square: BoardButton, override val context: Context, override var x : Float, override val y : Float, override val gameControl: GameControl) : Defense() {
    override val sizeInPx: Int = context.resources.displayMetrics.widthPixels / 12
    override var bitmap: Bitmap = resizeBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.sunflower))
    companion object { // Permet de récupérer prix sans instancier objet
        const val PRICE: Int = 2
    }
    override var HP = 1000
    private val sunCooldown: Int = 10000
    private val generationRadius = 100f
    private val timer = Timer()

    override fun getBoosted() {
        for (i in 1..5){
            createSun(generationRadius)
        }
    }

    init {
        startSunGeneration(generationRadius)
    }

    private fun createSun(radius: Float) { //créer un soleil à une position aléatoire (circulaire) autour de la plante
        val angle = Random.nextFloat() * (2 * Math.PI).toFloat()
        val distance = Random.nextFloat() * radius
        val sunX = x + distance * cos(angle)
        val sunY = y + distance * sin(angle)
        val sun = Sun(context, sunX, sunY, gameControl,false)
        gameControl.newResource.add(sun)
    }

    private fun startSunGeneration(radius: Float) {
        timer.schedule(timerTask {
            createSun(radius)
        }, 0, sunCooldown.toLong())
    }
    override fun nextFrame() {
        if (this.isDead()) {
            onDeath()
            timer.cancel() // Stop the timer
        }
    }





}

