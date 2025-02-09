package com.example.plantsvszombies

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable

@Suppress("DEPRECATION")
abstract class SelectableButton (context: Context, x: Double, y: Double, gameControl: GameControl, drawingView: DrawingView, widthPercent : Double, heightPercent: Double) : Button(context, x, y, gameControl, drawingView, widthPercent, heightPercent){
    abstract val imageResource: Int


    private fun changeSelectedButton() {
        gameControl.deselectAllButtons()
        if (gameControl.selectedButton == this) {
            gameControl.selectedButton = null

        } else {
            val previousButton = gameControl.selectedButton
            gameControl.selectedButton = this
            previousButton?.setBackground()
        }
        setBackground()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun setBackground() {
        val colorDrawable = if (gameControl.selectedButton == this) {
            ColorDrawable(Color.RED) // Rouge si le bouton est touché
        } else {
            null
        }
        val imageDrawable = resources.getDrawable(imageResource)
        val layers = mutableListOf<Drawable>(imageDrawable)
        colorDrawable?.let { layers.add(0, it) } // Ajoute colorDrawable en première position si non-null
        val layerDrawable = LayerDrawable(layers.toTypedArray())
        this.background = layerDrawable
    }

    fun setClickFunction() { //on définit ce que fait le bouton quand on clique dessus
        setupButton(imageResource) {
            changeSelectedButton()
        }
    }

}