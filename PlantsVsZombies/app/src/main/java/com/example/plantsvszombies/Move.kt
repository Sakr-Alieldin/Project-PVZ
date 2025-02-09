package com.example.plantsvszombies

interface Move {
    val velocity : Float //Vitesse en px qui varie en fonction de l'écran
    fun updatePosition(gameElement : GameElement){
        gameElement.x += velocity

    }
}