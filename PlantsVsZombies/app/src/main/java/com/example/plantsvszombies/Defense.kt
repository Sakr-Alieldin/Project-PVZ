package com.example.plantsvszombies

abstract class Defense : Entity() {
    abstract val square : BoardButton
    abstract fun getBoosted()
    override fun onDeath(){
        this.square.plantOnButton = null
        gameControl.removeDefence(this)
    }
}