package com.bdd.workshop.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.bdd.workshop.App;
import com.bdd.workshop.R;
import com.bdd.workshop.model.TaskManager;


public class AddTaskActivity extends AppCompatActivity {

  private TaskManager taskManager;
  private TextView titleView;
  private TextView descriptionView;

  @Override
  protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    taskManager = ((App) getApplication()).getTaskManager();

    setContentView(R.layout.activity_add_task);

    titleView = findViewById(R.id.titleText);
    descriptionView = findViewById(R.id.descriptionText);
    findViewById(R.id.fab).setOnClickListener(v -> onSaveTask());

  }

  private void onSaveTask() {
    if (!validate()) {
      return;
    }
    final String title = titleView.getText().toString();
    final String description = descriptionView.getText().toString();

    taskManager.saveTask(title, description);
    finish();
  }

  private boolean validate() {
    boolean valid = true;
    if (TextUtils.isEmpty(titleView.getText())) {
      valid = false;
      titleView.setError("Title may not be empty");
    }
    if (TextUtils.isEmpty(descriptionView.getText())) {
      valid = false;
      descriptionView.setError("Description may not be empty");

    }

    return valid;
  }
}
