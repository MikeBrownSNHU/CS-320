package Test;

import Task.Task;
import Task.TaskService;
import Task.TaskNotFoundException;
import Task.DuplicateTaskIdException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TaskServiceTest {

    private TaskService service;

    @BeforeEach
    void setup() {
        service = new TaskService();
    }

    @Test
    void canAddTaskAndReadItBack() {
        Task created = service.addTask("TSK01", "CodeReview", "Review merge requests for module A");

        Task fetched = service.getTask("TSK01");

        assertNotNull(created);
        assertSame(created, fetched);
        assertEquals("CodeReview", fetched.getName());
    }

    @Test
    void duplicateIdThrowsCustomException() {
        service.addTask("UNIQ9", "Deploy", "Prepare deployment checklist");

        assertThrows(DuplicateTaskIdException.class, () ->
                service.addTask("UNIQ9", "AnotherTask", "This should not be allowed")
        );
    }

    @Test
    void deleteExistingTaskRemovesIt() {
        service.addTask("DEL1", "CleanUp", "Clear temporary build artifacts");

        service.deleteTask("DEL1");

        assertThrows(TaskNotFoundException.class, () -> service.getTask("DEL1"));
    }

    @Test
    void deleteOnMissingIdThrowsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> service.deleteTask("MISSING_ID"));
    }

    @Test
    void updatesPropagateToStoredTask() {
        service.addTask("UPD1", "DraftPlan", "Write first version");

        service.updateName("UPD1", "FinalPlan");
        service.updateDescription("UPD1", "Completed plan after stakeholder review.");

        Task updated = service.getTask("UPD1");

        assertAll(
                () -> assertEquals("FinalPlan", updated.getName()),
                () -> assertEquals("Completed plan after stakeholder review.", updated.getDescription())
        );
    }

    @Test
    void updatingUnknownTaskThrowsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () ->
                service.updateDescription("NOBODY", "This should never work")
        );
    }

    @Test
    void invalidTaskDataStillFailsWhenAddedThroughService() {
        // Name longer than 20 should trigger validation in Task
        assertThrows(IllegalArgumentException.class, () ->
                service.addTask("EDGE7", "NameIsDefinitelyTooLongForSpec", "Some description")
        );
    }
    
    @Test
    void addingTaskWithNullIdFailsImmediately() {
        assertThrows(IllegalArgumentException.class, () ->
                service.addTask(null, "BackendFix", "Resolve timeout issue")
        );
    }
}
