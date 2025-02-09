
package com.example.plantsvszombies

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import java.util.Timer
import kotlin.concurrent.timerTask
import kotlin.random.Random
import kotlin.collections.mutableListOf



class GameControl(private val rootView: ViewGroup, val drawingView: DrawingView, val context: Context,val gameOverListener: GameOverListener){
    private val projectileList: MutableList<Projectile> = mutableListOf()
    val enemyList: MutableList<Enemy> = mutableListOf()
    val defenseList : MutableList<Defense> = mutableListOf()
    val defenseList1 = mutableListOf<Defense>()
    val defenseList2 = mutableListOf<Defense>()
    val defenseList3 = mutableListOf<Defense>()
    val defenseList4 = mutableListOf<Defense>()
    val defenseList5 = mutableListOf<Defense>()
    private val mowerList = mutableListOf<Lawnmower>()
    val resourceList = mutableListOf<Resource>()
    val newResource = mutableListOf<Resource>()
    val deadResource = mutableListOf<Resource>()
    private val tick = 0 //vitesse de déroulement du jeu(l'horloge)
    private val sequence = TaskSequencer()
    private var timer = Timer()
    private val newEnemy = mutableListOf<Enemy>() //liste temporaire de Enemy
    var deadProjectiles = mutableListOf<Projectile>()
    val deadEnemy = mutableListOf<Enemy>()
    val deadMowers = mutableListOf<Lawnmower>()
    val newProjectileList = mutableListOf<Projectile>()
    val displayMetrics = context.resources.displayMetrics!!
    val screenWidth = displayMetrics.widthPixels //In landscape mode width > height
    val screenHeight = displayMetrics.heightPixels
    private val shopImage: ImageView = ImageView(context)
    private val sunCountText: TextView = TextView(context)
    var sunCount: Int = 15
    var selectedButton: SelectableButton? = null
    val buttons = mutableListOf<SelectableButton>()
    val coordinates = coordinatesList()
    private val fertilizerImage: ImageView = ImageView(context)
    var fertilizerCount: Int = 3
    private val fertilizerCountText: TextView = TextView(context)
    private val factory = EntityFactory()

    init {
        startGameLoop()
        placeMowers()
        enemyGeneration()
        startNaturalSunGeneration()
        setCounter(-0.1, 0.05, shopImage, sunCountText, "Suns", 0.01f , sunCount)
        setCounter(-0.1, 0.25, fertilizerImage, fertilizerCountText, "Fertilizers", 0.008f, fertilizerCount)
    }

    private fun coordinatesList(): MutableList<MutableList<MutableList<Double>>> {
        val coordinates = mutableListOf<MutableList<MutableList<Double>>>()       //retourne une matrice de matrice de matrice de coordonnees
        for(i in 0 until 5){                                                   // du centre de chaque case en pourcentage de la taille de l'écran
            val matrix2D = mutableListOf<MutableList<Double>>()
            for(j in 0 until 9){
                val matrix1D = mutableListOf<Double>()
                matrix1D.add(0.38+j*0.077) //x
                matrix1D.add(0.197+i*0.162) //y
                matrix2D.add(matrix1D)
            }
            coordinates.add(matrix2D)
        }
        return coordinates
    }

    private fun addDefence(defense: Defense) {
        val yCoordinate = (defense.square.y / screenHeight).toDouble()
        val margin = 0.01

        when (yCoordinate) {
            in (coordinates[0][0][1] - 0.083 - margin)..(coordinates[0][0][1] - 0.083 + margin) -> defenseList1.add(defense)
            in (coordinates[1][0][1] - 0.083 - margin)..(coordinates[1][0][1] - 0.083 + margin) -> defenseList2.add(defense)
            in (coordinates[2][0][1] - 0.083 - margin)..(coordinates[2][0][1] - 0.083 + margin) -> defenseList3.add(defense)
            in (coordinates[3][0][1] - 0.083 - margin)..(coordinates[3][0][1] - 0.083 + margin) -> defenseList4.add(defense)
            in (coordinates[4][0][1] - 0.083 - margin)..(coordinates[4][0][1] - 0.083 + margin) -> defenseList5.add(defense)
        }
        for (zombie in enemyList){
            if ((zombie.y in (defense.square.y - 50)..(defense.square.y + 50) )&& defense is Shooter){
                zombie.addObserver(defense)
                zombie.notifyObservers(true)
            }
        }
    }
    fun removeDefence(defense: Defense){
        val yCoordinate = (defense.square.y / screenHeight).toDouble()
        val margin = 0.01 //Permet de prendre le centre de la case

        when (yCoordinate) {
            in (coordinates[0][0][1] - 0.083 - margin)..(coordinates[0][0][1] - 0.083 + margin) -> defenseList1.remove(defense)
            in (coordinates[1][0][1] - 0.083 - margin)..(coordinates[1][0][1] - 0.083 + margin) -> defenseList2.remove(defense)
            in (coordinates[2][0][1] - 0.083 - margin)..(coordinates[2][0][1] - 0.083 + margin) -> defenseList3.remove(defense)
            in (coordinates[3][0][1] - 0.083 - margin)..(coordinates[3][0][1] - 0.083 + margin) -> defenseList4.remove(defense)
            in (coordinates[4][0][1] - 0.083 - margin)..(coordinates[4][0][1] - 0.083 + margin) -> defenseList5.remove(defense)
        }
        for (zombie in enemyList){
            if ((zombie.y in (defense.square.y - 50)..(defense.square.y + 50) )&& defense is Shooter){
                zombie.removeObserver(defense)
            }
        }
    }

    fun addFertilizer() {
        fertilizerCount += 1
        fertilizerCountText.text = fertilizerCount.toString()
    }

    fun removeFertilizer() {
        fertilizerCount -= 1
        fertilizerCountText.text = fertilizerCount.toString()
    }

    private fun setCounter(x: Double, y: Double, image: ImageView, text: TextView, word:String, textSize:Float, count: Int ){
        val xInPx = x * screenHeight
        val yInPx = y * screenWidth
        val width = 0.2 * screenWidth
        val height = 0.2 * screenHeight
        val layoutParams = RelativeLayout.LayoutParams(width.toInt(), height.toInt())
        layoutParams.leftMargin = xInPx.toInt()
        layoutParams.topMargin = yInPx.toInt()

        // Configuration de l'image
        image.setImageResource(R.drawable.shopcadre)
        image.layoutParams = layoutParams

        // Configuration du texte
        text.text = count.toString()
        text.textSize = 0.01f * screenWidth // Taille du texte en fonction de la largeur de l'image
        text.setTextColor(Color.BLACK)
        text.gravity = Gravity.CENTER
        text.layoutParams = layoutParams

        // Configuration du texte "Suns"
        val otherText = TextView(context)
        otherText.text = word
        otherText.textSize = textSize * screenWidth // Taille du texte en fonction de la largeur de l'image
        otherText.setTextColor(Color.BLACK)
        otherText.gravity = Gravity.CENTER

        val sunsLayoutParams = RelativeLayout.LayoutParams(width.toInt(), height.toInt())
        sunsLayoutParams.leftMargin = xInPx.toInt()
        sunsLayoutParams.topMargin = (yInPx + height - 130).toInt() // Positionner en dessous du cadre
        otherText.layoutParams = sunsLayoutParams


        // Ajout de l'image et du texte
        rootView.addView(image)
        rootView.addView(text)
        rootView.addView(otherText)

    }

    fun deselectAllButtons() {

        for (button in buttons) {
            button.setBackground()
        }
    }

    fun addSun() {
        sunCount += 1
        sunCountText.text = sunCount.toString()
    }

    fun removeSun(cost: Int) {
        sunCount -= cost
        sunCountText.text = sunCount.toString()
    }

    private fun startGameLoop() {
        sequence.addTask({
            update()
            drawingView.invalidate()
            startGameLoop()
        }, tick.toLong()) //Framerate cappé à 60fps
    }

    private fun update() {

        for (element in projectileList + defenseList + enemyList + mowerList) {
            element.nextFrame()
        }

        for (sun in resourceList) {
            sun.nextFrame()
        }


        for (element in deadProjectiles) {
            projectileList.remove(element)
        }

        for(element in deadMowers){
            mowerList.remove(element)
        }

        for (element in deadEnemy) {
            enemyList.remove(element)
        }

        for (element in deadResource) {
            resourceList.remove(element)
            rootView.removeView(element)
        }

        for (resources in newResource) { //ajout des ressources
            rootView.addView(resources)
        }

        defenseList.clear()
        defenseList.addAll(defenseList1)
        defenseList.addAll(defenseList2)
        defenseList.addAll(defenseList3)
        defenseList.addAll(defenseList4)
        defenseList.addAll(defenseList5)
        enemyList.addAll(newEnemy) // ajoute les nouveaux Enemy à la liste de Enemy
        projectileList.addAll(newProjectileList)
        resourceList.addAll(newResource)
        drawingView.enemyList = this.enemyList // met à jour la liste de Enemy chez la vue
        drawingView.defenseList = defenseList // met à jour la liste de plantes chez la vue
        drawingView.projectileList = projectileList // met à jour la liste de projectiles chez la vue
        drawingView.resourceList = resourceList
        drawingView.mowerList = mowerList
        newEnemy.clear()
        newProjectileList.clear()
        newResource.clear()
        deadProjectiles.clear()
        deadEnemy.clear()
        deadResource.clear()
    }

    private fun startEnemyGeneration() {
        timer.schedule(
            timerTask {
                val yInPercent = (0.1 + 0.162 * Random.nextInt(0, 5))
                val randomNumber = Random.nextInt(1, 101)
                val enemyType = when {
                    randomNumber <= NormalZombie.generationProbability -> EnemyType.NormalZombie
                    randomNumber <= NormalZombie.generationProbability + ConeZombie.generationProbability -> EnemyType.ConeZombie
                    else -> EnemyType.ZombieSunStealer
                }
                createEnemy(enemyType, screenWidth.toFloat(), (yInPercent * screenHeight).toFloat())
            },
            0,
            (Random.nextInt(100, 1000) * 10).toLong()
        )
        drawingView.invalidate() // Redraws the view
    }

    private fun enemyGeneration(){ //recursive function, makes sure there are different waves of zombies every minute
        startEnemyGeneration()
        println("New wave of zombies ")
        timer.schedule(timerTask {
            timer.cancel()
            timer = Timer()
            enemyGeneration()
        }, 60000)
    }

    private fun placeMowers(){
        for (i in 0 until 5) {
            val yInPercent = (0.1 + 0.162 * i)
            val lawnmower = Lawnmower(this, context, (yInPercent * screenHeight).toFloat())
            mowerList.add(lawnmower)
        }
    }

    private fun startNaturalSunGeneration() {
        timer.schedule(timerTask {
            createNaturalSun()
        }, 0, (Random.nextInt(100, 1000) * 10).toLong())
        drawingView.invalidate()
    }

    private fun createEnemy(type: EnemyType, x: Float, y: Float) {
        val enemy = factory.createEnemy(type, this, drawingView.context, x, y)
        newEnemy.add(enemy)
        drawingView.invalidate()
    }

    fun createPlant(type: PlantType, square: BoardButton): Defense {
        val plant = factory.createPlant(type, square, this)
        addDefence(plant)
        drawingView.invalidate()
        return plant
    }

    private fun createNaturalSun() {
        val sun =
            Sun(context, Random.nextInt(0, screenWidth).toFloat() - 100f, -200f, this, true)
        newResource.add(sun)
    }
    fun endGame(){
        timer.cancel()
        enemyList.clear()
        gameOverListener.onGameOver()
    }
}



