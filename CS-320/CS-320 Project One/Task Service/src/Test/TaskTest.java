/*
 * Nate Riggs
 * CS-320 - Software Test, Automation QA
 * December 4, 2024
 */


package Test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import Task.Task;

public class TaskTest {
    
    private static Task task;  // Declare the task as static to be accessed by @BeforeAll

    @BeforeAll
    static void setUp() {
        // One-time setup for all tests
        task = new Task("12345", "Test", "Test task");
    }

    @Test
    @DisplayName("Test successful task creation")
    void testTask() {
        assertEquals("12345", task.getTaskId());
        assertEquals("Test", task.getTaskName());
        assertEquals("Test task", task.getTaskDescription());
    }

    @Test
    @DisplayName("Test setTaskName method")
    void testSetTaskName() {
        task.setTaskName("New Task");
        assertEquals("New Task", task.getTaskName());
    }

    @Test
    @DisplayName("Test setTaskDescription method")
    void testSetTaskDescription() {
        task.setTaskDescription("New Task Description");
        assertEquals("New Task Description", task.getTaskDescription());
    }

    @Test
    @DisplayName("Test taskId being too long")
    void testTaskIdTooLong() {
        // Ensure an exception is thrown when the Task ID exceeds 10 characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("01234510123456", "Test", "Test task");
        });
    }

    @Test
    @DisplayName("Test taskId being null")
    void testTaskIdBeingNull() {
        // Ensure an exception is thrown when the Task ID is null
        assertThrows(IllegalArgumentException.class, () -> {
            new Task(null, "Peter", "Test task");
        });
    }

    @Test
    @DisplayName("Test taskName being too long")
    void testTaskNameTooLong() {
        // Ensure an exception is thrown when the Task Name exceeds 20 characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("123456", "Testabcdefghijklmnop1", "Test task");
        });
    }

    @Test
    @DisplayName("Test taskName being null")
    void testTaskNameBeingNull() {
        // Ensure an exception is thrown when the Task Name is null
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("123456", null, "Test task");
        });
    }

    @Test
    @DisplayName("Test taskDescription being too long")
    void testTaskDescriptionTooLong() {
        // Ensure an exception is thrown when the Task Description is more than 50 characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("123456", "Test", "Testdescription0123456789101112131415161718192021222324252627282930");
        });
    }

    @Test
    @DisplayName("Test taskDescription being null")
    void testTaskDescriptionNull() {
        // Ensure an exception is thrown when the Task Description is null
        assertThrows(IllegalArgumentException.class, () -> {
            new Task("123456", "Peeter", null);
        });
    }
}
