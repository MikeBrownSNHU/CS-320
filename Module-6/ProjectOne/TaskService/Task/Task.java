package Task;

/**
 * Task represents a unit of work with a unique ID, a name,
 * and a description. The ID is immutable, while name and
 * description can be updated with validation.
 */
public class Task {

    private final String taskId;   // <= 10, not null
    private String name;           // <= 20, not null
    private String description;    // <= 50, not null

    public Task(String taskId, String name, String description) {
        this.taskId = validateId(taskId);
        this.name = validateName(name);
        this.description = validateDescription(description);
    }

    // ----- Getters -----
    public String getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // ----- Setters for updatable fields -----
    public void setName(String name) {
        this.name = validateName(name);
    }

    public void setDescription(String description) {
        this.description = validateDescription(description);
    }

    // ----- Validation helpers -----
    private String validateId(String id) {
        if (id == null || id.length() > 10) {
            throw new IllegalArgumentException("Task ID must be non-null and at most 10 characters.");
        }
        return id;
    }

    private String validateName(String name) {
        if (name == null || name.length() > 20) {
            throw new IllegalArgumentException("Task name must be non-null and at most 20 characters.");
        }
        return name;
    }

    private String validateDescription(String description) {
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Task description must be non-null and at most 50 characters.");
        }
        return description;
    }
}
