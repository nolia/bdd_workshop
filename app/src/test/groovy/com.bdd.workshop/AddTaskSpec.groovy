
package com.bdd.workshop

import android.content.ComponentName
import android.widget.EditText
import com.bdd.workshop.model.TaskManager
import com.bdd.workshop.ui.AddTaskActivity
import com.bdd.workshop.ui.MainActivity
import hkhc.electricspock.ElectricSpecification
import org.robolectric.Robolectric
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows

class AddTaskSpec extends ElectricSpecification {

  TaskManager mockTaskManager = Mock() {
    getAllTasks() >> []
  }

  void setup() {
    def app = RuntimeEnvironment.application as App
    app.taskManager = mockTaskManager
  }

  def "should open AddTask screen from TaskList"() {
    given: "I am on Task List"
    def mainActivity = Robolectric.setupActivity(MainActivity)

    when: "I click on Add Task button"
    mainActivity.findViewById(R.id.fab).performClick()

    then: "I'm brought to AddTask screen"
    def nextActivityIntent = Shadows.shadowOf(mainActivity).peekNextStartedActivity()
    nextActivityIntent.component == new ComponentName(mainActivity, AddTaskActivity)

  }

  def "validate task before saving"() {
    given: "I am on AddTask screen"
    def activity = Robolectric.setupActivity(AddTaskActivity)
    def title = activity.findViewById(R.id.titleText) as EditText
    def description = activity.findViewById(R.id.descriptionText) as EditText

    when: "I try to save a task with empty title and description"
    title.setText("")
    description.setText("")
    activity.findViewById(R.id.fab).performClick()

    then: "Task is not saved"
    0 * mockTaskManager.saveTask(_, _)

    and: "I can see a corresponding error"
    title.error == "Title may not be empty"
    description.error == "Description may not be empty"
  }

  def "should save valid task"() {
    given: "I'm on Add Task screen"
    def activity = Robolectric.setupActivity(AddTaskActivity)
    def title = activity.findViewById(R.id.titleText) as EditText
    def description = activity.findViewById(R.id.descriptionText) as EditText

    when: "I input valid task title and description"
    title.text = "Task #1"
    description.text = "Task description"

    and: "I perform save action"
    activity.findViewById(R.id.fab).performClick()

    then: "Task is saved"
    1 * mockTaskManager.saveTask("Task #1", "Task description")

    and: "Add Task screen is closed"
    activity.isFinishing()

  }
}