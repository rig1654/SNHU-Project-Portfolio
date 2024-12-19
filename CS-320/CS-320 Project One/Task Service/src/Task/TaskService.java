/*
 * Nate Riggs
 * CS-320 - Software Test, Automation QA
 * December 4, 2024
 */

package Task;

import java.util.ArrayList;

public class TaskService {
	
	int currentId = 0;
	
	public static ArrayList<Task> taskList = new ArrayList<Task>();
	
	public void addTask(String taskName, String taskDescription) {
		
		// Convert currentId to string to create new task
		String idToString = Integer.toString(currentId);
		
		
		// Create new task
		Task newTask = new Task(idToString, taskName, taskDescription);
		
		// Add new task to task list
		taskList.add(newTask);
		
		// Increment task Id
		++currentId;	
	}
	
	// Method to delete contact based on contact ID
	public void deleteTask(String taskId) {
	    // Convert taskId to integer
	    boolean taskFound = false;

	    for (int i = 0; i < TaskService.taskList.size(); i++) {
	        if (TaskService.taskList.get(i).getTaskId().equals(taskId)) {
	            TaskService.taskList.remove(i);
	            taskFound = true;
	            break;  // Exit the loop once the task is found and removed
	        }
	    }
	    
	    if (!taskFound) {
	        throw new IllegalArgumentException("Task with ID " + taskId + " does not exist.");
	    }
	}
		
		// Method to update taskName based on taskId
		public void updateTaskName(String taskId, String taskName) {
		    for (Task i : taskList) {
		        if (i.getTaskId().equals(taskId)) {
		            i.setTaskName(taskName);
		        }
		    }
		}
		
		// Method to update taskDescription based on taskId
				public void updateTaskDescription(String taskId, String taskDescription) {
				    for (Task i : taskList) {
				        if (i.getTaskId().equals(taskId)) {
				            i.setTaskDescription(taskDescription);
				        }
				    }
				}
}
