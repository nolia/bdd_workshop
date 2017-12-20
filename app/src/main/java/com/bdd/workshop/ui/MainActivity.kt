package com.bdd.workshop.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bdd.workshop.App
import com.bdd.workshop.R
import com.bdd.workshop.model.Task
import com.bdd.workshop.model.TaskManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_task.view.*

class MainActivity : AppCompatActivity() {

  private lateinit var taskManager: TaskManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    taskManager = (application as App).taskManager

    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)

    val taskAdapter = TaskAdapter(LayoutInflater.from(this))
    recyclerView.adapter = taskAdapter
    recyclerView.layoutManager = LinearLayoutManager(this)
    taskAdapter.tasks = taskManager.getAllTasks()

    fab.setOnClickListener {
      startActivity(Intent(this, AddTaskActivity::class.java))
    }
  }
}

private class TaskAdapter(val inflater: LayoutInflater): RecyclerView.Adapter<TaskViewHolder>() {

  var tasks: List<Task> = ArrayList()
    set(value) {
      field = value
      notifyDataSetChanged()
    }


  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TaskViewHolder =
      TaskViewHolder(inflater.inflate(R.layout.item_task, parent, false))

  override fun onBindViewHolder(holder: TaskViewHolder?, position: Int) {
    holder?.updateTask(tasks[position])
  }

  override fun getItemCount(): Int = tasks.size

}

class TaskViewHolder(view: View): RecyclerView.ViewHolder(view) {

  fun updateTask(task: Task) {
    itemView.title.text = task.title
    itemView.description.text = task.description
  }

}
