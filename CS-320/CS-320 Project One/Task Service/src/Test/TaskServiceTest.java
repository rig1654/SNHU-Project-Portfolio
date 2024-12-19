/*
 * Nate Riggs
 * CS-320 - Software Test, Automation QA
 * December 4, 2024
 */

package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Task.TaskService;

public class TaskServiceTest {
	
	private TaskService taskService;
	private String taskName = "Task 1";
	private String taskDescription = "This is a task description.";
	
	@BeforeEach
	void setUp() {
        taskService = new TaskService();  // Create a fresh instance of TaskService before each test
        taskService.addTask(taskName, taskDescription);  // Add a task to the list
    }
	
	@AfterEach
	void tearDown() {
        TaskService.taskList.clear();  // Clear the task list after each test to avoid interference
    }
	
	
    @Test
    @DisplayName("Test addTask")
    void testAddTask() {
        assertFalse(TaskService.taskList.isEmpty());  // Ensure task list is not empty
        assertEquals("0", TaskService.taskList.get(0).getTaskId());  // Verify task ID
        assertEquals(taskName, TaskService.taskList.get(0).getTaskName()); //Verify task name
        assertEquals(taskDescription, TaskService.taskList.get(0).getTaskDescription()); //Verify task description
    }
    
    @Test
    @DisplayName("test deleteTask with valid task ID")
    void  testDeleteTaskValid() {
    	// Adding more tasks to test deletion
        taskService.addTask("Test 2", "Test Description 2");
        taskService.addTask("Test 3", "Test Description 3");

        assertEquals(3, TaskService.taskList.size());  // Verify there are 3 tasks

        taskService.deleteTask("1");  // Delete the task with ID "1"
        
        assertEquals(2, TaskService.taskList.size());  // Verify the list size is reduced
        
        // Boolean to test if task exists in list
        boolean task1Exists = false;
        
        // Check if the task with ID "1" still exists
        task1Exists = TaskService.taskList.stream().anyMatch(task -> "1".equals(task.getTaskId())); // Check if task with ID "1" still exists

        assertFalse(task1Exists);  // Assert the task with ID "1" is no longer present
        
        
        //Verify that other remaining task Ids are present
        boolean task0Exists = TaskService.taskList.stream().anyMatch(task -> "0".equals(task.getTaskId()));
        boolean task2Exists = TaskService.taskList.stream().anyMatch(task -> "2".equals(task.getTaskId()));
        
        assertTrue(task0Exists);
        assertTrue(task2Exists);
    }
    
    
    
    @Test
    @DisplayName("test deleteTask with invalid task ID")
    void testDeleteTaskInvalid() {
    	taskService.addTask("Task 2", "Description 1");

    	assertThrows(IllegalArgumentException.class, () -> {
        taskService.deleteTask("3");  // Task with ID "3" does not exist
    });
}
    
    @Test
    @DisplayName("Test updateTaskName")
    void testUpdateTaskName() {
        // Verify the initial first name
        assertEquals(taskName, TaskService.taskList.get(0).getTaskName());

        taskService.updateTaskName("0", "TestTask");  // Update taskname
        
        // Verify that the task name is updated
        assertEquals("TestTask", TaskService.taskList.get(0).getTaskName());
    }
    
    @Test
    @DisplayName("Test updateTaskDescription")
    void testUpdateTaskDescription() {
        // Verify the initial task description
        assertEquals(taskDescription, TaskService.taskList.get(0).getTaskDescription());

        taskService.updateTaskDescription("0", "TestDescription");  // Update task description
        
        // Verify that the test description is updated
        assertEquals("TestDescription", TaskService.taskList.get(0).getTaskDescription());
    }
    
}

