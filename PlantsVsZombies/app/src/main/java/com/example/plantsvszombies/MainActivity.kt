@file:Suppress("DEPRECATION")

package com.example.plantsvszombies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup

class MainActivity : AppCompatActivity(), GameOverListener{
    private lateinit var drawingView: DrawingView
    private lateinit var gameControl: GameControl

    //Fonctions pour cacher la barre de navigation et la barre de notifications
    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rootView = findViewById<ViewGroup>(android.R.id.content)
        hideSystemUI() //Cacher la barre de navigation et la barre de notification
        drawingView = findViewById(R.id.vMain)
        drawingView.setWillNotDraw(false)
        drawingView.invalidate()
        gameControl = GameControl(rootView  ,drawingView = drawingView, context = this, gameOverListener = this)

        val coordinates = gameControl.coordinates

        val shop = Shop(this, gameControl, drawingView, rootView)
        shop.setupButtons()

        val board = Board(this, gameControl, drawingView, rootView, coordinates)
        board.setupButtons()
        }


    override fun onResume() {
        super.onResume()
        hideSystemUI()
    }

    override fun onGameOver() {
        val intent = Intent(this, EndGameActivity::class.java)
        startActivity(intent)
        finish()// terminer la MainActivity
    }

}
