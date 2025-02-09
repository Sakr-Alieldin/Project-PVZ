package com.example.plantsvszombies

abstract class Entity : GameElement() {
    protected abstract var HP : Int
    fun changeHP(damage : Int){
        this.HP -= damage
    }

    override fun  isDead() : Boolean{
        return (this.HP <= 0)
    }
}