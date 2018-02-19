package com.bdd.workshop.model;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

  private ArrayList<Task> items = new ArrayList<>();

  public void saveTask(String title, String description) {
    items.add(new Task(title, description));
  }

  public List<Task> getAllTasks() {
    return items;
  }
}
