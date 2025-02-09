package com.example.plantsvszombies


import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("ViewConstructor")
class ShovelButton(context: Context, x: Double, y: Double, gameControl: GameControl, drawingView: DrawingView, widthPercent: Double, heightPercent: Double) : SelectableButton(context, x, y, gameControl, drawingView, widthPercent, heightPercent){
    override val imageResource: Int = R.drawable.shovel
    init {
        gameControl.buttons.add(this)
    }

}