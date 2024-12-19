/*
 * Nate Riggs
 * CS-320 - Software Test, Automation QA
 * December 4, 2024
 */

package Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
import Appointment.AppointmentService;


public class AppointmentServiceTest {
	
	private AppointmentService appointmentService;
	private Date appointmentDate;
	private String appointmentDescription = "Test Appointment Description";
	
	@BeforeEach
	void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1); // Future date.
        appointmentDate = calendar.getTime(); // Assigning a valid date.

        appointmentService = new AppointmentService();
        appointmentService.addAppointment(appointmentDate, appointmentDescription);
    }
	
	@AfterEach
	void tearDown() {
		AppointmentService.appointmentList.clear();
	}
	
	@Test
	@DisplayName("Test Appointment Creation")
	void TestAddAppointment() {
		assertFalse(AppointmentService.appointmentList.isEmpty());
		assertEquals("0", AppointmentService.appointmentList.get(0).getAppointmentID());
		assertEquals(appointmentDate, AppointmentService.appointmentList.get(0).getAppointmentDate());
		assertEquals(appointmentDescription, AppointmentService.appointmentList.get(0).getAppointmentDescription());
	}

	@Test
	@DisplayName("Test Delete Appointment with valid Appointment ID")
	void testDeleteAppointmentValid() {
		appointmentService.addAppointment(appointmentDate, "Test Description 1");
		appointmentService.addAppointment(appointmentDate, appointmentDescription);
		
		assertEquals(3, AppointmentService.appointmentList.size());
		
		appointmentService.deleteAppointment("1");
		
		assertEquals(2, AppointmentService.appointmentList.size());
		
		boolean appointmentExists = false;
		
		for (int i = 0; i < AppointmentService.appointmentList.size(); i++) {
			if ("1".equals(AppointmentService.appointmentList.get(i).getAppointmentID())) {
				appointmentExists = true;
				break;
			}
		}
		
		assertFalse(appointmentExists);
	}
	
	@Test
	@DisplayName("Test delete Appointment from an empty list")
	void testDeleteFromEmptyList() {
	    AppointmentService.appointmentList.clear(); // Ensure the list is empty
	    assertThrows(IllegalArgumentException.class, () -> {
	        appointmentService.deleteAppointment("0"); // Deleting from an empty list
	    });
	}

	
	@Test
	@DisplayName("Test delete Appointment with invalid Appointment ID")
	void testDeleteAppointmentInvalid() {
		appointmentService.addAppointment(appointmentDate, appointmentDescription);
		
		assertThrows(IllegalArgumentException.class, () -> {
			appointmentService.deleteAppointment("3");
		});
	}
}
