package com.example.plantsvszombies
import kotlin.random.Random

abstract class Enemy(override val gameControl: GameControl,override val y: Float) : Entity(), Attack, TargetGetter, Move,Observable{
    private var motionState: MotionState = Advancing(this)
    override val direction = TargetingMode.LEFT
    var previousState : MotionState = motionState
    val defenseList = setDefenseList()
    val burgerList = mutableSetOf<BurgerDefense>()
    private val containFertilizer = Random.nextInt(1, 6) == 1 //1 chance sur 5 de drop un engrais
    override val velocity = -1f
    final override var observers = setObservers()
    private var burningTicks = 0
    private var freezingTicks = 0
    var isBurning = false
        set(value){
            field = value
            if (field){
                burningTicks = 60
            }
        }
    var isFreezing = false
        set(value){
            field = value
            if (field){
                freezingTicks = 60
            }
        }

    init {
        val sameLineZombies = gameControl.enemyList.filter { it.y == this.y && it != this }
        if (sameLineZombies.isEmpty()) {
                notifyObservers(true)

        }
    }

    override fun isDead(): Boolean {
        return (super.isDead() || this.x < 0)
    }

    override fun onDeath() {
        if (containFertilizer) {
            gameControl.newResource.add(Fertilizer(context,this.x, this.y, gameControl))
        }
        val sameLineZombies = gameControl.enemyList.filter { it.y == this.y && it != this }
        if (sameLineZombies.isEmpty()) {
                notifyObservers(false)
        }
        gameControl.deadEnemy.add(this)
    }

    override fun nextFrame() {
        super.nextFrame()
        if (freezingTicks > 0) {
            freezingTicks--
            if (freezingTicks == 0) {
                // The freezing effect has ended
                isFreezing = false
            }
        }

        if (burningTicks > 0) {
            burningTicks--
            if (burningTicks == 0) {
                // The burning effect has ended
                isBurning = false
            }
        }
        updatePosition(this)
        if (x < 0) {
           gameControl.endGame()
        }
    }

    override fun updatePosition(gameElement: GameElement) {
        motionState.updatePosition(gameElement)
    }

    private fun setDefenseList(): MutableList<Defense> {
        val coList = listOf(0.1, 0.262, 0.424, 0.586, 0.748) // positions of the zombies in screen percentage defined in gameControl
        val margin = 1
        for (i in 0..4){
            if ((coList[i]*gameControl.screenHeight).toFloat() in (y- margin)..(y + margin)){
                println("Zombie on line $i")
                when(i) {
                    0 -> return gameControl.defenseList1
                    1 -> return gameControl.defenseList2
                    2 -> return gameControl.defenseList3
                    3 -> return gameControl.defenseList4
                    4 -> return gameControl.defenseList5
                }
            }
        }
        return mutableListOf()
    }

    private fun setObservers(): MutableList<Observer> {
        val observers = mutableListOf<Observer>()
        for (defense in defenseList){
            if (defense is Shooter){
                observers.add(defense)
            }
        }
        return observers
    }

    fun changeMotionState(motionState: MotionState){
        this.motionState = motionState
    }

    fun getAttracted(burger : BurgerDefense){
        burgerList.add(burger)
    }



}
