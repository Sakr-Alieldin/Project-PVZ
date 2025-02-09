package com.example.plantsvszombies

interface Attack {
    val attackDamage : Int
    fun attack(target: Entity?) {
        target?.changeHP(attackDamage)
    }
}