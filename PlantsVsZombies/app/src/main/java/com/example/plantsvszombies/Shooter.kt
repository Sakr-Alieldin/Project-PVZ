package com.example.plantsvszombies
import android.content.Context
import java.util.Timer
import kotlin.concurrent.timerTask

abstract class Shooter : ActiveDefense(),Observer{
    private var timer : Timer = Timer()
    protected abstract val shootingDelay : Int

    override fun onDeath(){
        super.onDeath()
        timer.cancel() // Stop the timer
    }

    override fun attack(target : Entity?){
        startShooting()
    }

    override fun getBoosted() {
        val boostedTimer = Timer()

        val task = timerTask {
            boostedShootProjectile(this@Shooter.context)
        }

        // Schedule the task to run immediately and repeat every second
        boostedTimer.schedule(task, 0, 100)

        // Stop the task after 5 seconds
        Timer().schedule(timerTask {
            boostedTimer.cancel()
        }, 5000)
    }

    override fun update(arg: Boolean) {
        timer.cancel() // Stop the timer
        if (arg){
            timer = Timer() // create a new Timer object
            attack(null)
        }

    }

    protected abstract fun shootProjectile(context: Context)
    protected abstract fun boostedShootProjectile(context: Context)
    private fun startShooting() {
        timer.schedule(timerTask {
            shootProjectile(this@Shooter.context)
        }, 0, shootingDelay.toLong()) //

    }


}