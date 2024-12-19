/*
 * Nate Riggs
 * CS-320 - Software Test, Automation QA
 * December 4, 2024
 */

package Appointment;

import java.util.Date;
import java.util.ArrayList;



public class AppointmentService {
	
	int currentID = 0;
	
	public static ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
	
	public void addAppointment(Date appointmentDate, String appointmentDescription) {
		// Convert currentId to string to create new appointment
		String idToString = Integer.toString(currentID);
		
		// Create new appointment
		Appointment newAppointment = new Appointment(idToString, appointmentDate, appointmentDescription);
				
		// Add new appointment appointmentList
		appointmentList.add(newAppointment);
				
		// Increment appointment ID
		++currentID;	
	}
	
	// Method to delete appointment based on appointment ID
		public void deleteAppointment(String appointmentID) {
		    // Create boolean for finding appointment
		    boolean appointmentFound = false;

		    for (int i = 0; i < AppointmentService.appointmentList.size(); i++) {
		        if (AppointmentService.appointmentList.get(i).getAppointmentID().equals(appointmentID)) {
		            AppointmentService.appointmentList.remove(i);
		            appointmentFound = true;
		            break;  // Exit the loop once the appointment is found and removed
		        }
		    }
		    
		    // Check if appointment deleted
		    if (!appointmentFound) {
		        throw new IllegalArgumentException("Appointment with ID " + appointmentID + " does not exist.");
		    }
		}

}
