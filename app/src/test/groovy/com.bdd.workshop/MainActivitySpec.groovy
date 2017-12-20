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

  void setup() {
    controller = Robolectric.buildActivity(MainActivity)
    mainActivity = controller.get()

    def app = RuntimeEnvironment.application as App
    app.taskManager = mockTaskManager
  }

  def "should see tasks on task list"() {
    given: "I have a bunch of tasks"
    def task = new Task(1, "first", "task")

    when: "I open task list screen"
    controller.create().visible()

    then: "I should see these tasks"
    1 * mockTaskManager.getAllTasks() >> [task]

    def recycler = mainActivity.findViewById(R.id.recyclerView) as RecyclerView
    recycler.visibility == View.VISIBLE
    recycler.childCount == 1

    shouldMatchViewAndTask(recycler.getChildAt(0), task)
  }

  private def shouldMatchViewAndTask(View view, Task task) {
    def titleView = view.findViewById(R.id.title) as TextView
    def descriptionView = view.findViewById(R.id.description) as TextView

    return task.title == titleView.getText().toString() &&
           task.description == descriptionView.getText().toString()
  }
}