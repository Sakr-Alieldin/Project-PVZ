package com.example.plantsvszombies


import android.annotation.SuppressLint
import android.content.Context





@SuppressLint("ViewConstructor")
class ShopButton(context: Context, x: Double, y: Double, gameControl: GameControl, drawingView: DrawingView, widthPercent : Double, heightPercent: Double, val plantType: PlantType, override val imageResource: Int) : SelectableButton(context, x, y, gameControl, drawingView, widthPercent, heightPercent){
    val price: Int= when (plantType) {
        PlantType.Fertilizer -> 1
        PlantType.Sunflower -> Sunflower.PRICE
        PlantType.NormalPlant -> NormalPlant.PRICE
        PlantType.FreezePlant -> IcePlant.PRICE
        PlantType.FirePlant -> FirePlant.PRICE
        PlantType.BurgerDefense -> BurgerDefense.PRICE
        PlantType.Wallnut -> Wallnut.PRICE
        PlantType.KnockbackPlant -> KnockbackPlant.PRICE
        PlantType.FastPlant -> FastPlant.PRICE
    }
    init {
        gameControl.buttons.add(this)
    }

}






