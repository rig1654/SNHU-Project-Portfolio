/*
 * Nate Riggs
 * CS-320 - Software Test, Automation QA
 * December 4, 2024
 */


package Appointment;

import java.util.Date;

public class Appointment {
	
	private String appointmentID;
	private Date appointmentDate;
	private String appointmentDescription;
	
	public  Appointment(String appointmentID, Date appointmentDate, String appointmentDescription) {
		
		 // Validate Appointment fields
       validateAppointmentID(appointmentID);
       validateAppointmentDate(appointmentDate);
       validateAppointmentDescription(appointmentDescription);
       
       this.appointmentID = appointmentID;
       this.appointmentDate = appointmentDate;
       this.appointmentDescription = appointmentDescription;
	}
	
	// Check if ID is null or longer than 10 dcharacters
	public static void validateAppointmentID(String appointmentID) {
		if (appointmentID == null || appointmentID.length() > 10) {
			throw new IllegalArgumentException("Invalid Appointment ID");
				}
		}
	
	// Check if Appointment date is null or in the past
	public static void validateAppointmentDate(Date appointmentDate) {
		if (appointmentDate == null || appointmentDate.before(new Date())) {
			throw new IllegalArgumentException("Invalid Appointment Date");
		}
	}
	
	// Check if appointment Description is null or longer than 50 characters
	public static void validateAppointmentDescription(String appointmentDescription) {
		if (appointmentDescription == null || appointmentDescription.length() > 50) {
			throw new IllegalArgumentException("Invalid Appointment Description");
		}
	}
	
	// Getter method for appointmentID
	public String getAppointmentID() {
		return appointmentID;
		}
			
	// Getter method for appointmentDate
	public Date getAppointmentDate() {
		return appointmentDate;
		}
	
	// Setter method for appointmentDate
	public void setAppointmentDate(Date appointmentDate) {
		validateAppointmentDate(appointmentDate);
		this.appointmentDate = appointmentDate;
	}
			
	// Getter method for appointmentDescription
	public String getAppointmentDescription() {
		return appointmentDescription;
	}
	
	// Setter method for appointmentDescription
	public void setAppointmentDescription(String appointmentDescription) {
		validateAppointmentDescription(appointmentDescription);
		this.appointmentDescription = appointmentDescription;
	}
	
	
	
	

}