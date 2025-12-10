package Task;

import java.util.HashMap;
import java.util.Map;

/**
 * TaskService maintains a simple in-memory collection of Task objects.
 * It supports adding, updating, and deleting tasks, enforcing unique IDs
 * and surfacing clear error conditions.
 */
public class TaskService {

    private final Map<String, Task> taskStore = new HashMap<>();

    public Task addTask(String id, String name, String description) {
        if (id == null) {
            throw new IllegalArgumentException("Task ID cannot be null.");
        }

        if (taskStore.containsKey(id)) {
            throw new DuplicateTaskIdException("Task ID already exists: " + id);
        }

        Task t = new Task(id, name, description);
        taskStore.put(id, t);
        return t;
    }

    public void deleteTask(String id) {
        if (!taskStore.containsKey(id)) {
            throw new TaskNotFoundException("Task not found for deletion: " + id);
        }
        taskStore.remove(id);
    }

    public void updateName(String id, String newName) {
        getExistingTask(id).setName(newName);
    }

    public void updateDescription(String id, String newDescription) {
        getExistingTask(id).setDescription(newDescription);
    }

    public Task getTask(String id) {
        return getExistingTask(id);
    }

    // Internal helper method keeps "not found" logic in one place
    private Task getExistingTask(String id) {
        Task t = taskStore.get(id);
        if (t == null) {
            throw new TaskNotFoundException("Task not found: " + id);
        }
        return t;
    }
}
