package com.example.plantsvszombies

import android.content.Context
import android.widget.FrameLayout


abstract class Button(context: Context, x: Double, y: Double, val gameControl: GameControl, private val drawingView: DrawingView, widthPercent: Double = 0.077, heightPercent: Double =0.167 ): androidx.appcompat.widget.AppCompatButton(context) {


    private val widthPx = gameControl.screenWidth * widthPercent //pc = pourcentage
    private val heightPx = gameControl.screenHeight * heightPercent
    private var layoutparams = FrameLayout.LayoutParams(widthPx.toInt(), heightPx.toInt())
    val valueX = gameControl.screenWidth * x.toFloat()
    val valueY = gameControl.screenHeight * y.toFloat()
    private val pxValueX = valueX.toInt()
    private val pxValueY = valueY.toInt()

    init {
        layoutparams.leftMargin = pxValueX
        layoutparams.topMargin = pxValueY
        this.layoutParams = layoutparams
    }

    fun setInvisible() {
        this.alpha = 0f
    }

    fun setupButton(drawableId: Int, onClickAction: () -> Unit) {
        setBackgroundResource(drawableId)
        setOnClickListener {onClickAction()}
    }

}



