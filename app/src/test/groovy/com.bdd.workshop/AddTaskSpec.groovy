
package com.bdd.workshop

import android.content.ComponentName
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
}