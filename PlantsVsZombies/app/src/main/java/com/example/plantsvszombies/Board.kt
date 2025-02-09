package com.example.plantsvszombies

import android.content.Context
import android.view.ViewGroup

class Board(private val context: Context, private val gameControl: GameControl, private val drawingView: DrawingView, private val rootView: ViewGroup, private val coordinates: MutableList<MutableList<MutableList<Double>>>) {
    fun setupButtons() {
        for(line in coordinates){
            for(position in line){
                val posX = position[0] - 0.038
                val posY = position[1] - 0.083

                val myButton = BoardButton(context, posX, posY, gameControl, drawingView).apply {
                    setInvisible()
                    setOnClickListener {
                        placePlant()
                    }
                }
                rootView.addView(myButton)
            }
        }
    }
}