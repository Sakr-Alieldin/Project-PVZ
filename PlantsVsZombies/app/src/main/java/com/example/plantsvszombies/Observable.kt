package com.example.plantsvszombies

interface Observable {
    var observers: MutableList<Observer>

    fun addObserver(observer: Observer){
        observers.add(observer)
    }

    fun removeObserver(observer: Observer){
        observers.remove(observer)
    }

    fun notifyObservers(arg: Boolean) { //l'arg est un flag pour que la plante sâche si elle doit tirer ou non
        for(observer in observers){
            observer.update(arg)
        }
    }
}