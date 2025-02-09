package com.example.plantsvszombies

import android.graphics.BitmapFactory
import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceView
import android.view.View
import java.util.*


class DrawingView @JvmOverloads constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr) {
    private val screenWidth = context.resources.displayMetrics.widthPixels // largeur de l'écran
    private val screenHeight = context.resources.displayMetrics.heightPixels // hauteur de l'écran

    var enemyList: MutableList<Enemy> = mutableListOf<Enemy>()
        set(value) {
            field = value
            invalidate() // redessine la vue quand la liste change
        }
    var defenseList = mutableListOf<Defense>()
        set(value) {
            field = value
            invalidate()
        }
    var projectileList = mutableListOf<Projectile>()
        set(value) {
            field = value
            invalidate()
        }
    var resourceList = mutableListOf<Resource>()
        set(value) {
            field = value
            invalidate()
        }
    var mowerList = mutableListOf<Lawnmower>()
        set(value) {
            field = value
            invalidate()
        }


    init {
        // met le fond d'écran

        val background = BitmapDrawable(
            context.resources,
            BitmapFactory.decodeResource(context.resources, R.drawable.background_final)
        )
        background.setBounds(0, 0, screenWidth, screenHeight)
        this.background = background

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Draw all GameElements
        for (element in projectileList + defenseList + enemyList + mowerList) {
            element.draw(canvas)
        }
        for (resource in resourceList) { //N'est pas un gameElement (c'est une View)
            resource.draw(canvas)
        }
    }
}





