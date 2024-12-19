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

import Contact.ContactService;

public class ContactServiceTest {

    private ContactService contactService;
    private String firstName = "Nate";
    private String lastName = "Riggs";
    private String phoneNumber = "1234567890";
    private String address = "123 Fake St.";

    // Called before each test
    @BeforeEach
    void setUp() {
        contactService = new ContactService();  // Create a fresh instance of ContactService before each test
        contactService.addContact(firstName, lastName, phoneNumber, address);  // Add a contact to the list
    }

    // Called after each test
    @AfterEach
    void tearDown() {
        ContactService.contactList.clear();  // Clear the contact list after each test to avoid interference
    }

    @Test
    @DisplayName("Test addContact")
    void testAddContact() {
        assertFalse(ContactService.contactList.isEmpty());  // Ensure contact list is not empty
        assertEquals("0", ContactService.contactList.get(0).getContactId());  // Verify contact ID
        assertEquals(firstName, ContactService.contactList.get(0).getFirstName());  // Verify first name
        assertEquals(lastName, ContactService.contactList.get(0).getLastName());  // Verify last name
        assertEquals(phoneNumber, ContactService.contactList.get(0).getPhoneNumber());  // Verify phone number
        assertEquals(address, ContactService.contactList.get(0).getAddress());  // Verify address
    }

    @Test
    @DisplayName("Test deleteContact")
    void testDeleteContact() {
        // Adding more contacts to test deletion
        contactService.addContact("John", "Doe", "9876543210", "456 Another St.");
        contactService.addContact("Jane", "Smith", "1122334455", "789 Another St.");

        assertEquals(3, ContactService.contactList.size());  // Verify there are 3 contacts

        contactService.deleteContact("1");  // Delete the contact with ID "1"
        
        assertEquals(2, ContactService.contactList.size());  // Verify the list size is reduced
        boolean contactExists = false;
        
        // Check if the contact with ID "1" still exists
        for (int i = 0; i < ContactService.contactList.size(); i++) {
            if ("1".equals(ContactService.contactList.get(i).getContactId())) {
                contactExists = true;
                break;
            }
        }

        assertFalse(contactExists);  // Assert the contact with ID "1" is no longer present
    }

    @Test
    @DisplayName("Test updateFirstName")
    void testUpdateFirstName() {
        // Verify the initial first name
        assertEquals(firstName, ContactService.contactList.get(0).getFirstName());

        contactService.updateFirstName("0", "TestName");  // Update first name
        
        // Verify that the first name is updated
        assertEquals("TestName", ContactService.contactList.get(0).getFirstName());
    }

    @Test
    @DisplayName("Test updateLastName")
    void testUpdateLastName() {
        // Verify the initial last name
        assertEquals(lastName, ContactService.contactList.get(0).getLastName());

        contactService.updateLastName("0", "TestName");  // Update last name
        
        // Verify that the last name is updated
        assertEquals("TestName", ContactService.contactList.get(0).getLastName());
    }

    @Test
    @DisplayName("Test updatePhoneNumber")
    void testUpdatePhoneNumber() {
        // Verify the initial phone number
        assertEquals(phoneNumber, ContactService.contactList.get(0).getPhoneNumber());

        contactService.updatePhoneNumber("0", "0987654321");  // Update phone number
        
        // Verify that the phone number is updated
        assertEquals("0987654321", ContactService.contactList.get(0).getPhoneNumber());
    }

    @Test
    @DisplayName("Test updateAddress")
    void testUpdateAddress() {
        // Verify the initial address
        assertEquals(address, ContactService.contactList.get(0).getAddress());

        contactService.updateAddress("0", "321 Fake St.");  // Update address
        
        // Verify that the address is updated
        assertEquals("321 Fake St.", ContactService.contactList.get(0).getAddress());
    }


}
