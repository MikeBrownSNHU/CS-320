
package Task;

import java.util.HashMap;
import java.util.Map;

public class TaskService {

    // simple in-memory store, keyed by taskId
    private final Map<String, Task> tasks = new HashMap<>();

    // Add a new Task using field values
    public void addTask(String taskId, String name, String description) {
        if (tasks.containsKey(taskId)) {
            throw new IllegalArgumentException("Task ID must be unique.");
        }

        Task task = new Task(taskId, name, description);
        tasks.put(taskId, task);
    }

    // Optionally, add an existing Task object (still enforce unique ID)
    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }
        String taskId = task.getTaskId();
        if (tasks.containsKey(taskId)) {
            throw new IllegalArgumentException("Task ID must be unique.");
        }
        tasks.put(taskId, task);
    }

    // Delete a task by ID
    public void deleteTask(String taskId) {
        if (!tasks.containsKey(taskId)) {
            throw new IllegalArgumentException("Task with ID " + taskId + " does not exist.");
        }
        tasks.remove(taskId);
    }

    // Update name for task ID
    public void updateTaskName(String taskId, String newName) {
        Task task = getTask(taskId);
        task.setName(newName);
    }

    // Update description for task ID
    public void updateTaskDescription(String taskId, String newDescription) {
        Task task = getTask(taskId);
        task.setDescription(newDescription);
    }

    // Helper to get a task (useful for tests)
    public Task getTask(String taskId) {
        Task task = tasks.get(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Task with ID " + taskId + " does not exist.");
        }
        return task;
    }
}
