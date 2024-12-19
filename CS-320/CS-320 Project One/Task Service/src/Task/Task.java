/*
 * Nate Riggs
 * CS-320 - Software Test, Automation QA
 * December 4, 2024
 */


package Task;

public class Task {

		private String taskId;
		private String taskName;
		private String taskDescription;
		public  Task(String taskId, String taskName, String taskDescription) {
			
			 // Validate taskId and taskName
	        validateTaskId(taskId);
	        validateTaskName(taskName);
	        validateTaskDescription(taskDescription);
	        
	        this.taskId = taskId;
	        this.taskName = taskName;
	        this.taskDescription = taskDescription;
	
		}
			
			
			
			// Check if Id is null or longer than 10 digits
		public static void validateTaskId(String taskId) {
			if (taskId == null || taskId.length() > 10) {
				throw new IllegalArgumentException("Invalid Task ID");
					}
			}
			
			// Check if task name is null or longer than 20 characters
		public static void validateTaskName(String taskName) {
			if(taskName == null || taskName.length() > 20) {
				throw new IllegalArgumentException("Invalid Task Name.");
			}
		}
		
		// Check if task description is null or longer than 50 characters
		public static void validateTaskDescription(String taskDescription) {
			if (taskDescription == null || taskDescription.length() > 50) {
				throw new IllegalArgumentException("Invalid Task Descripition");
			}
		}
		// Getter method for taskID
		public String getTaskId() {
			return taskId;
		}
		
		// Getter method for taskName
		public String getTaskName() {
			return taskName;
		}
		
		// Getter method for taskDescription
		public String getTaskDescription() {
			return taskDescription;
		}
		
		//Setter method for taskName
		public void setTaskName(String taskName) {
			validateTaskName(taskName);
			this.taskName =  taskName;
		}
		
		//Setter Method for taskDescription
		public void setTaskDescription(String taskDescription) {
			validateTaskDescription(taskDescription);
			this.taskDescription =  taskDescription;
		}
		
}
			
			 

