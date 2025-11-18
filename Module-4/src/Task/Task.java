
package Task;

public class Task {

    private final String taskId;      
    private String name;
    private String description;

    // ----- Constructor -----

    public Task(String taskId, String name, String description) {
        validateTaskId(taskId);
        validateName(name);
        validateDescription(description);

        this.taskId = taskId;
        this.name = name;
        this.description = description;
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

   

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    // ----- Validation helpers -----

    private void validateTaskId(String taskId) {
        if (taskId == null || taskId.length() > 10) {
            throw new IllegalArgumentException("Task ID must be non-null and at most 10 characters.");
        }
    }

    private void validateName(String name) {
        if (name == null || name.length() > 20) {
            throw new IllegalArgumentException("Name must be non-null and at most 20 characters.");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Description must be non-null and at most 50 characters.");
        }
    }
}
