package com.bdd.workshop

import android.app.Application
import com.bdd.workshop.model.TaskManager

class App: Application() {

  lateinit var taskManager: TaskManager

  override fun onCreate() {
    super.onCreate()
    taskManager = TaskManager()
  }

}