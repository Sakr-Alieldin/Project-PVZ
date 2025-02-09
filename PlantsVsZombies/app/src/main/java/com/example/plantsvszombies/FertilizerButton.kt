package com.example.plantsvszombies

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("ViewConstructor")
class FertilizerButton (context: Context, x: Double, y: Double, gameControl: GameControl, drawingView: DrawingView, widthPercent: Double, heightPercent: Double) : SelectableButton(context, x, y, gameControl, drawingView, widthPercent, heightPercent){
    override val imageResource: Int = R.drawable.fertiliser
    init {
        gameControl.buttons.add(this)
    }

}