package com.bdd.workshop.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bdd.workshop.App;
import com.bdd.workshop.R;
import com.bdd.workshop.model.Task;
import com.bdd.workshop.model.TaskManager;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private TaskManager taskManager;

  private RecyclerView recyclerView;

  @Override
  protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    taskManager = ((App) getApplication()).getTaskManager();

    setContentView(R.layout.activity_main);
    setSupportActionBar(findViewById(R.id.toolbar));

    recyclerView = findViewById(R.id.recyclerView);
    final TaskAdapter taskAdapter = new TaskAdapter(LayoutInflater.from(this));
    recyclerView.setAdapter(taskAdapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    taskAdapter.setTasks(taskManager.getAllTasks());
  }


  static class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {

    private final LayoutInflater inflater;
    private List<Task> items = Collections.emptyList();

    TaskAdapter(final LayoutInflater inflater) {
      this.inflater = inflater;
    }

    void setTasks(List<Task> tasks) {
      this.items = tasks != null ? tasks : Collections.emptyList();
      notifyDataSetChanged();
    }

    @Override
    public TaskViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
      return new TaskViewHolder(inflater.inflate(R.layout.item_task, parent, false));
    }

    @Override
    public void onBindViewHolder(final TaskViewHolder holder, final int position) {
      holder.updateTask(items.get(position));
    }

    @Override
    public int getItemCount() {
      return items.size();
    }
  }


  static class TaskViewHolder extends RecyclerView.ViewHolder {


    private TextView title;
    private TextView description;

    public TaskViewHolder(final View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.title);
      description = itemView.findViewById(R.id.description);
    }

    void updateTask(Task task) {
      title.setText(task.getTitle());
      description.setText(task.getDescription());
    }
  }
}
