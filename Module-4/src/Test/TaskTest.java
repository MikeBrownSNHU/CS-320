
package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Task.Task;

public class TaskTest {

    @Test
    void constructorValidDataCreatesTask() {
        Task task = new Task("1234567890", "Test Task", "This is a test description.");

        assertEquals("1234567890", task.getTaskId());
        assertEquals("Test Task", task.getName());
        assertEquals("This is a test description.", task.getDescription());
    }

    @Test
    void taskIdCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> 
            new Task(null, "Name", "Description")
        );
    }

    @Test
    void taskIdCannotBeLongerThanTenCharacters() {
        assertThrows(IllegalArgumentException.class, () -> 
            new Task("12345678901", "Name", "Description") // 11 chars
        );
    }

    @Test
    void nameCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> 
            new Task("12345", null, "Description")
        );
    }

    @Test
    void nameCannotBeLongerThanTwentyCharacters() {
        String longName = "This name is definitely too long";
        assertThrows(IllegalArgumentException.class, () -> 
            new Task("12345", longName, "Description")
        );
    }

    @Test
    void descriptionCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> 
            new Task("12345", "Name", null)
        );
    }

    @Test
    void descriptionCannotBeLongerThanFiftyCharacters() {
        String longDescription = "This description is definitely longer than fifty characters in length.";
        assertThrows(IllegalArgumentException.class, () -> 
            new Task("12345", "Name", longDescription)
        );
    }

    @Test
    void taskIdIsNotUpdatableButNameAndDescriptionAre() {
        Task task = new Task("12345", "Old Name", "Old description.");

        task.setName("New Name");
        task.setDescription("New description.");

        // ID stays the same
        assertEquals("12345", task.getTaskId());
        assertEquals("New Name", task.getName());
        assertEquals("New description.", task.getDescription());
    }
}