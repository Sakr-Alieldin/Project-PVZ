package com.example.plantsvszombies

import android.os.Handler
import android.os.Looper


class TaskSequencer {
    private val handler = Handler(Looper.getMainLooper())
    private var delay: Long = 0

    fun addTask(task: () -> Unit, delayMillis: Long) {  /*fait une tâche, attend le delay et fait la prochaine task*/
        delay = delayMillis
        handler.postDelayed({
            task()
        }, delay)
    }
}