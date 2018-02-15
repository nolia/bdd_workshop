package com.bdd.workshop

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.bdd.workshop.model.Task
import com.bdd.workshop.model.TaskManager
import com.bdd.workshop.ui.MainActivity
import hkhc.electricspock.ElectricSpecification
import org.robolectric.Robolectric
import org.robolectric.RuntimeEnvironment
import org.robolectric.android.controller.ActivityController

class MainActivitySpec extends ElectricSpecification {

  private ActivityController<MainActivity> controller
  private MainActivity mainActivity

  private TaskManager mockTaskManager = Mock()
  private App app

  void setup() {
    controller = Robolectric.buildActivity(MainActivity)
    mainActivity = controller.get()

    app = RuntimeEnvironment.application as App
  }

  def "should see tasks on task list"() {
    given: "I have a bunch of tasks"

    app.taskManager = mockTaskManager
    def task = new Task("first", "task")

    when: "I open task list screen"
    controller.create().visible()

    then: "I should see these tasks"
    1 * mockTaskManager.getAllTasks() >> [task]

    def recycler = mainActivity.findViewById(R.id.recyclerView) as RecyclerView
    recycler.visibility == View.VISIBLE
    recycler.childCount == 1

    shouldMatchViewAndTask(recycler.getChildAt(0), task.title, task.description)
  }

  def "should update UI when task list is updated"() {
    given: "I have a task list"
    def task1 = new Task("task1", "hello")
    app.taskManager = new TaskManager([task1])

    when: "I open task list scree"
    controller.create().visible()

    and: "Add a new task"
    app.taskManager.saveTask("task2", "world")

    then: "I should see it on UI"
    def recycler = mainActivity.findViewById(R.id.recyclerView) as RecyclerView
    recycler.visibility == View.VISIBLE
    recycler.childCount == 2

    shouldMatchViewAndTask(recycler.getChildAt(0), task1.title, task1.description)
    shouldMatchViewAndTask(recycler.getChildAt(1), "task2", "world")

  }

  private def shouldMatchViewAndTask(View view, String title, String description) {
    def titleView = view.findViewById(R.id.title) as TextView
    def descriptionView = view.findViewById(R.id.description) as TextView

    return title == titleView.getText().toString() &&
           description == descriptionView.getText().toString()
  }
}