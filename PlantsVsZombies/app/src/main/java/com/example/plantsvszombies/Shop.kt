package com.example.plantsvszombies

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.TextView

class Shop(private val context: Context, private val gameControl: GameControl, private val drawingView: DrawingView, private val rootView: ViewGroup) {
    fun setupButtons() {
        val shovelButton = ShovelButton(context, 0.3, 0.0,gameControl, drawingView, 0.1,0.1)
        val shopButton1 = ShopButton(context, 0.1, 0.07,gameControl, drawingView, 0.05,0.1, PlantType.Sunflower, R.drawable.sunflower)
        val shopButton2 = ShopButton(context, 0.1, 0.25,gameControl, drawingView, 0.05,0.1 ,PlantType.NormalPlant, R.drawable.peashooter)
        val shopButton3 = ShopButton(context, 0.1, 0.4,gameControl, drawingView, 0.05,0.1 ,PlantType.FastPlant, R.drawable.fastplant)
        val shopButton4 = ShopButton(context, 0.1, 0.55,gameControl, drawingView, 0.05,0.1, PlantType.FreezePlant, R.drawable.freezingplant)
        val shopButton5 = ShopButton(context, 0.1, 0.7,gameControl, drawingView, 0.05,0.1, PlantType.KnockbackPlant, R.drawable.knockbackplant)
        val shopButton6 = ShopButton(context, 0.1, 0.85,gameControl, drawingView, 0.05,0.1, PlantType.Wallnut, R.drawable.musculardoge)
        val shopButton7 = ShopButton(context, 0.2, 0.07,gameControl, drawingView, 0.05,0.1, PlantType.Fertilizer, R.drawable.fertiliser)
        val shopButton8 = ShopButton(context, 0.2, 0.25,gameControl, drawingView, 0.05,0.1 ,PlantType.BurgerDefense, R.drawable.burger)
        val shopButton9 = ShopButton(context, 0.2, 0.4,gameControl, drawingView, 0.05,0.1 ,PlantType.FirePlant, R.drawable.fireplant)
       // val shopButton10 = ShopButton(context, 0.2, 0.55,gameControl, drawingView, 0.05,0.1, PlantType.FreezePlant, R.drawable.freezingplant)
        //val shopButton11 = ShopButton(context, 0.2, 0.7,gameControl, drawingView, 0.05,0.1, PlantType.BurgerDefense, R.drawable.burger)
       // val shopButton12 = ShopButton(context, 0.2, 0.85,gameControl, drawingView, 0.05,0.1, PlantType.Wallnut, R.drawable.musculardoge)

        shopButton1.setClickFunction()
        shopButton2.setClickFunction()
        shopButton3.setClickFunction()
        shopButton4.setClickFunction()
        shopButton5.setClickFunction()
        shopButton6.setClickFunction()
        shovelButton.setClickFunction()
        shopButton7.setClickFunction()
        shopButton8.setClickFunction()
        shopButton9.setClickFunction()
        //shopButton10.setClickFunction()
        //shopButton11.setClickFunction()
        //shopButton12.setClickFunction()


        rootView.addView(shopButton1)
        addPriceTextAtCoordinates(0.1, 0.17, shopButton1)
        rootView.addView(shopButton2)
        addPriceTextAtCoordinates(0.1, 0.35, shopButton2)
        rootView.addView(shopButton3)
        addPriceTextAtCoordinates(0.08, 0.5, shopButton3)
        rootView.addView(shovelButton)
        rootView.addView(shopButton4)
        addPriceTextAtCoordinates(0.1, 0.65, shopButton4)
        rootView.addView(shopButton5)
        addPriceTextAtCoordinates(0.1, 0.8, shopButton5)
        rootView.addView(shopButton6)
        addPriceTextAtCoordinates(0.1, 0.95, shopButton6)
        rootView.addView(shopButton7)
        addPriceTextAtCoordinates(0.2, 0.17, shopButton7)
        rootView.addView(shopButton8)
        addPriceTextAtCoordinates(0.2, 0.35, shopButton8)
        rootView.addView(shopButton9)
        addPriceTextAtCoordinates(0.2, 0.5, shopButton9)
        //rootView.addView(shopButton10)
       // addPriceTextAtCoordinates(0.2, 0.65, shopButton10)
       // rootView.addView(shopButton11)
        //addPriceTextAtCoordinates(0.2, 0.8, shopButton11)
        //rootView.addView(shopButton12)
       // addPriceTextAtCoordinates(0.2, 0.95, shopButton12)

    }
    @SuppressLint("SetTextI18n")
    fun addPriceTextAtCoordinates(xPercent: Double, yPercent: Double, shopButton: ShopButton){
        val priceTextView = TextView(context)
        if (shopButton.plantType == PlantType.Fertilizer) {
            priceTextView.text = "${shopButton.price} Fertilizer"
        } else {
            priceTextView.text = "${shopButton.price} Suns"
        }
        priceTextView.setTextColor(Color.BLACK) // Set text color to black
        priceTextView.setTypeface(null, Typeface.BOLD) // Set text style to bold

        val displayMetrics = context.resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels

        priceTextView.x = (screenWidth * xPercent).toFloat()
        priceTextView.y = (screenHeight * yPercent).toFloat()

        rootView.addView(priceTextView)
    }
}