package com.bdd.workshop;

import android.app.Application;

import com.bdd.workshop.model.TaskManager;

public class App extends Application {

  private TaskManager taskManager;

  @Override
  public void onCreate() {
    super.onCreate();
    taskManager = new TaskManager();
  }

  public TaskManager getTaskManager() {
    return taskManager;
  }

  public void setTaskManager(final TaskManager taskManager) {
    this.taskManager = taskManager;
  }
}
