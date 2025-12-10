package Test;

import Task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TaskTest {

    @Test
    void acceptsValidValuesAtLengthLimits() {
        String id10 = "TASK_98765";            // 10 chars
        String name20 = "FeatureSpecUpdate99"; // 20 chars
        String desc50 = "Finish sprint demo slides and notes by Friday!!!"; // 50 chars

        Task task = new Task(id10, name20, desc50);

        assertEquals(id10, task.getTaskId());
        assertEquals(name20, task.getName());
        assertEquals(desc50, task.getDescription());
    }

    @Test
    void idCannotBeNullOrTooLong() {
        assertThrows(IllegalArgumentException.class, () ->
                new Task(null, "Review", "Desc")
        );

        String longId = "ABCDEFGHIJK"; // 11 chars
        assertThrows(IllegalArgumentException.class, () ->
                new Task(longId, "Review", "Desc")
        );
    }

    @Test
    void nameMustFollowLengthConstraints() {
        String longName = "ThisNameIsDefinitelyTooLong"; // > 20 chars

        assertThrows(IllegalArgumentException.class, () ->
                new Task("ID01", longName, "Check config files")
        );

        assertThrows(IllegalArgumentException.class, () ->
                new Task("ID02", null, "Null name should fail")
        );
    }

    @Test
    void descriptionMustFollowLengthConstraints() {
        String over50 = "123456789012345678901234567890123456789012345678901"; // 51 chars

        assertThrows(IllegalArgumentException.class, () ->
                new Task("ID03", "Name", over50)
        );

        assertThrows(IllegalArgumentException.class, () ->
                new Task("ID04", "Name", null)
        );
    }

    @Test
    void settersReuseSameValidationLogic() {
        Task t = new Task("T1", "Draft", "Initial draft");

        t.setName("RefinedPlan");
        t.setDescription("Refine test strategy notes before code review.");

        assertEquals("RefinedPlan", t.getName());
        assertEquals("Refine test strategy notes before code review.", t.getDescription());
    }

    @Test
    void settersRejectBadUpdates() {
        Task t = new Task("T2", "ShortName", "Short description");

        assertThrows(IllegalArgumentException.class, () -> t.setName("ThisNameIsWayTooLongForTheLimits"));
        assertThrows(IllegalArgumentException.class, () -> t.setDescription(
                "This description deliberately runs past fifty characters to trigger a failure."
        ));
    }
}
