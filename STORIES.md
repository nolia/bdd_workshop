# Stories & features

### As a user I should be able to see my task list
given: "I have a bunch of tasks"
when: "I open task list screen"
then: "I should see these tasks"

### As a user I should be able to add a task.
- open Add Task form/screen

```
given: "I am on Task List"
when: "I click on Add button"
then: "I am brought to Add Task screen"
```

- save task from Add Task form/screen
```
given: "I'm on Add Task screen"
when: "I input valid task title and description"
and: "I perform save action"
then: "Task is saved"
and: "Add Task screen is closed"
```

- validate task before saving
```
given: "I'm on Add Task screen"
when: "I try to save a task with empty title or description"
then: "Task is not saved"
and: "I can see a corresponding error"
```

- task list should be updated after new task is saved


### As a user I should be able to delete a task.

### As a user I should be able to edit a task.