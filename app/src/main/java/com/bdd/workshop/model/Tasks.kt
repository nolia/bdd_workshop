package com.bdd.workshop.model

data class Task(
    var title: String,
    var description: String
)


open class TaskManager(
    tasks: List<Task> = emptyList()
) {

  private val items: MutableList<Task> = ArrayList(tasks)
  var taskObserver: (() -> Unit)? = null

  open fun getAllTasks(): List<Task> {
    return items
  }

  open fun saveTask(title: String, description: String) {
    items.add(Task(title, description))
    taskObserver?.invoke()
  }

}