package com.example.plantsvszombies

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("ViewConstructor")
class BoardButton(context: Context, x: Double, y: Double, gameControl: GameControl, drawingView: DrawingView, widthInPercent: Double = 0.077, heightInPercent: Double =0.167) : Button(context, x, y, gameControl, drawingView, widthInPercent, heightInPercent){
    var plantOnButton: Defense? = null

        fun placePlant() {
            if (plantOnButton != null) {
                if (gameControl.selectedButton is ShovelButton) {
                    plantOnButton!!.changeHP(1000000) //tue la plante
                    plantOnButton = null
                }else {
                    gameControl.selectedButton?.let { button ->
                        if (button is ShopButton && button.plantType == PlantType.Fertilizer && gameControl.fertilizerCount > 0) {
                            plantOnButton!!.getBoosted()
                            gameControl.removeFertilizer()
                        }
                    }
                }
            } else {
                gameControl.selectedButton?.let { button ->
                    if (button is ShopButton && button.plantType != PlantType.Fertilizer) {
                        if (gameControl.sunCount >= button.price) {
                            plantOnButton = gameControl.createPlant(button.plantType, this)
                            gameControl.removeSun(button.price)
                            gameControl.selectedButton = null
                            button.setBackground()
                        }
                    }
                }
            }
        }

            }



