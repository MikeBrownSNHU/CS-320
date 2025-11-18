package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import Task.Task;
import Task.TaskService;

public class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void addTaskWithUniqueIdSucceeds() {
        taskService.addTask("ID1", "Task One", "Description one");
        taskService.addTask("ID2", "Task Two", "Description two");

        Task t1 = taskService.getTask("ID1");
        Task t2 = taskService.getTask("ID2");

        assertEquals("Task One", t1.getName());
        assertEquals("Task Two", t2.getName());
    }

    @Test
    void addTaskWithDuplicateIdThrowsException() {
        taskService.addTask("ID1", "Task One", "Description one");

        assertThrows(IllegalArgumentException.class, () -> 
            taskService.addTask("ID1", "Another Task", "Another description")
        );
    }

    @Test
    void deleteExistingTaskRemovesIt() {
        taskService.addTask("ID1", "Task One", "Description one");

        taskService.deleteTask("ID1");

        assertThrows(IllegalArgumentException.class, () -> 
            taskService.getTask("ID1")
        );
    }

    @Test
    void deleteNonExistingTaskThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> 
            taskService.deleteTask("NOPE")
        );
    }

    @Test
    void updateTaskNameChangesOnlyName() {
        taskService.addTask("ID1", "Old Name", "Same description");
        taskService.updateTaskName("ID1", "New Name");

        Task task = taskService.getTask("ID1");

        assertEquals("New Name", task.getName());
        assertEquals("Same description", task.getDescription());
        assertEquals("ID1", task.getTaskId());
    }

    @Test
    void updateTaskDescriptionChangesOnlyDescription() {
        taskService.addTask("ID1", "Same Name", "Old description");
        taskService.updateTaskDescription("ID1", "New description");

        Task task = taskService.getTask("ID1");

        assertEquals("Same Name", task.getName());
        assertEquals("New description", task.getDescription());
        assertEquals("ID1", task.getTaskId());
    }
}