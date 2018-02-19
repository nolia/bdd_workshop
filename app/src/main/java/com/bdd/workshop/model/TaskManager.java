package com.bdd.workshop.model;

import android.support.v4.util.ArraySet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TaskManager {

  private ArrayList<Task> items = new ArrayList<>();

  private Set<Listener> observers = new ArraySet<>();

  public void saveTask(String title, String description) {
    items.add(new Task(title, description));
    for (Listener observer : observers) {
      observer.onTasksChanged();
    }
  }

  public List<Task> getAllTasks() {
    return items;
  }

  public void removeListener(final Listener o) {
    observers.remove(o);
  }

  public void addListener(final Listener o) {
    observers.add(o);
  }

  public interface Listener {
    void onTasksChanged();
  }
}
