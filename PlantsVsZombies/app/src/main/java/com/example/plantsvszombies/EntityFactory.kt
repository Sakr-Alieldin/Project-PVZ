package com.example.plantsvszombies
import android.content.Context


class EntityFactory {
    fun createPlant(type: PlantType, square: BoardButton, gameControl: GameControl): Defense {
        return when (type) {
            PlantType.NormalPlant -> NormalPlant(square, gameControl.drawingView.context, square.valueX, square.valueY, gameControl)
            PlantType.Sunflower -> Sunflower(square, gameControl.drawingView.context, square.valueX, square.valueY, gameControl)
            PlantType.Wallnut -> Wallnut(gameControl, square, gameControl.drawingView.context, square.valueX, square.valueY)
            PlantType.BurgerDefense -> BurgerDefense(gameControl, square, gameControl.drawingView.context, square.valueX, square.valueY)
            PlantType.FreezePlant -> IcePlant(square, gameControl.drawingView.context, square.valueX, square.valueY, gameControl)
            PlantType.FastPlant -> FastPlant(square, gameControl.drawingView.context, square.valueX, square.valueY, gameControl)
            PlantType.KnockbackPlant -> KnockbackPlant(square, gameControl.drawingView.context, square.valueX, square.valueY, gameControl)
            PlantType.FirePlant -> FirePlant(square, gameControl.drawingView.context, square.valueX, square.valueY, gameControl)
            else -> throw IllegalArgumentException("Invalid plant type")
        }
    }
    fun createEnemy(type: EnemyType, gameControl: GameControl, context: Context, x: Float, y: Float): Enemy {
        return when (type) {
            EnemyType.NormalZombie -> NormalZombie(gameControl, context, x, y)
            EnemyType.ZombieSunStealer -> ZombieSunStealer(gameControl, context, x, y)
            EnemyType.ConeZombie -> ConeZombie(gameControl, context, x, y)
        }
    }
}