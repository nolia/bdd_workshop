package com.bdd.workshop.model

data class Task(
    val id: Long,
    var title: String,
    var description: String)


open class TaskManager {

  private val items: List<Task> = ArrayList()

  open fun getAllTasks(): List<Task> {
      return items
  }

  open fun saveTask(title: String, description: String) {
    // TODO
  }

}