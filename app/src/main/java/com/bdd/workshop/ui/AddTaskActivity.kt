package com.bdd.workshop.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils.isEmpty
import com.bdd.workshop.App
import com.bdd.workshop.R
import com.bdd.workshop.model.TaskManager
import kotlinx.android.synthetic.main.activity_add_task.*

class AddTaskActivity: AppCompatActivity() {

  private lateinit var taskManager: TaskManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    taskManager = (application as App).taskManager

    setContentView(R.layout.activity_add_task)
    setSupportActionBar(toolbar)

    fab.setOnClickListener {
      saveTask()
    }
  }

  private fun saveTask() {
    if (validateInput()) {
      val title = titleText.text.toString().trim()
      val description = descriptionText.text.toString().trim()
      taskManager.saveTask(title, description)
      finish()
    }
  }

  private fun validateInput(): Boolean {
    var result = true

    if (isEmpty(titleText.text)) {
      titleText.error = "Title may not be empty"
      result = false
    }

    if (isEmpty(descriptionText.text)) {
      descriptionText.error = "Description may not be empty"
      result = false
    }

    return result
  }
}