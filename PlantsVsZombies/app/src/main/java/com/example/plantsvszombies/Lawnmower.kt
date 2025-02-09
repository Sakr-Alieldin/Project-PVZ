package com.example.plantsvszombies

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory


//Ici tous les attributs sont les mêmes, on pourrait faire des companion object et puis override les valeurs, mais flemme
//demander à github copilot si nécessaire
class Lawnmower(override val gameControl: GameControl,override val context : Context, override var y: Float): GameElement(), Attack, TargetGetter, Move {
    override var x: Float = 0.25f * context.resources.displayMetrics.widthPixels
    override val range = 100
    override val attackDamage = 10000
    override val sizeInPx = context.resources.displayMetrics.heightPixels / 7
    override var bitmap: Bitmap = resizeBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.lawnmower))
    override val velocity = 5f //Vitesse en px qui varie en fonction de l'écran
    private val defenseList = gameControl.defenseList
    private val enemyList = gameControl.enemyList
    private var activated = false
    override val direction = TargetingMode.RIGHT

    override fun onDeath(){
        gameControl.deadMowers.add(this)
    }

    override fun nextFrame() {
        super.nextFrame()
        val enemy = getTargets(enemyList).firstOrNull()
        if(enemy != null){
            attack(enemy)
            activated = true
        }
        val defense = getTargets(defenseList).firstOrNull()
        if(defense!= null){
            attack(defense)
            activated = true
        }
        if(activated) {
            updatePosition(this)
        }
    }
}