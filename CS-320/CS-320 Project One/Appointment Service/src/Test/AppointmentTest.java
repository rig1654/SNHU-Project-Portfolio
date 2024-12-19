/*
 * Nate Riggs
 * CS-320 - Software Test, Automation QA
 * December 4, 2024
 */

package Test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.Calendar;
import static org.junit.jupiter.api.Assertions.*;

import Appointment.Appointment;

public class AppointmentTest {
	
	private Appointment appointment;
	private Date appointmentDate;
	

	@BeforeEach
    void setUp() {
		// Create a future appointment date (one day from now)
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1); // Add 1 day to current date
		appointmentDate = calendar.getTime();
		appointment = new Appointment("12345", appointmentDate, "Test appointment");
	}
	
	@Test
	@DisplayName("Test appointment creation")
	void testAppointmentTask() {
        assertEquals("12345", appointment.getAppointmentID());
        assertEquals(appointmentDate.getTime(), appointment.getAppointmentDate().getTime());
        assertEquals("Test appointment", appointment.getAppointmentDescription());
    }
	
	@Test
	@DisplayName("Test setAppointmentDate with valid date")
	void testSetAppointmentDateValid() {
	    Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.DAY_OF_YEAR, 1); // Add 1 day for a future date
	    Date validDate = calendar.getTime();

	    // Set the appointment date using the setter
	    appointment.setAppointmentDate(validDate);
	    assertEquals(validDate.getTime(), appointment.getAppointmentDate().getTime()); // Verify the date is set correctly
	}
	
	@Test
	@DisplayName("Test setAppointmentDate with past date")
	void testSetAppointmentDateInvalid() {
	    Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.DAY_OF_YEAR, -1); // Add 1 day for a past date
	    Date pastDate = calendar.getTime();

	    // Try to set the appointment date to the past and check for exception
	    assertThrows(IllegalArgumentException.class, () -> {
	        appointment.setAppointmentDate(pastDate); // Should throw exception
	    });
	}
	
	@Test
	@DisplayName("Test setAppointmentDescription with valid description")
	void testSetAppointmentDescriptionValid() {
	    String validDescription = "Valid description within the 50 characters limit.";

	    // Set the appointment description using the setter
	    appointment.setAppointmentDescription(validDescription);
	    assertEquals(validDescription, appointment.getAppointmentDescription()); // Verify the description is set correctly
	}
	@Test
	@DisplayName("Test setAppointmentDescription with null description")
	void testSetAppointmentDescriptionNull() {
	    // Try to set the appointment description to null and check for exception
	    assertThrows(IllegalArgumentException.class, () -> {
	        appointment.setAppointmentDescription(null); // Should throw exception
	    });
	}
	
	@Test
	@DisplayName("Test Appointment ID too long")
	void testAppointmentIDTooLong() {
		assertThrows(IllegalArgumentException.class, () -> {
			// Create an appointment with an ID longer than 10 characters
			new Appointment("01234510123456", appointmentDate,  "Test appointment");
		});
	}
	
	@Test
	@DisplayName("Test Appointment ID equals null")
	void testAppointmentIDNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			// Create an appointment with an ID longer than 10 characters
			new Appointment(null, appointmentDate,  "Test appointment");
		});
	}
	
	@Test
	@DisplayName("Test Appointment Date is null")
	void testAppointmentDateNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			// Create an appointment with an ID longer than 10 characters
			new Appointment("12345", null,  "Test appointment");
		});
	}
	@Test
	@DisplayName("Test Appointment Date is in the past")
	void testAppointmentDateInThePast() {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1); // Add -1 day to go into the past
		Date pastDate = calendar.getTime();
		
		// Test Appointment creation with a past date
		assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("12345", pastDate, "Test appointment");
		});
	}
	
	@Test
	@DisplayName("Test Appointment Description too long")
	void testAppointmentDescriptionTooLong() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("12345", appointmentDate, "This appointment is to schedule a time to allow me to do something that will take longer than 50 characters!");
		});
	}
	
	@Test
	@DisplayName("Test Appointment Description is null")
	void testAppointmentDescriptionNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("12345", appointmentDate, null);
		});
	}
	
	
}
