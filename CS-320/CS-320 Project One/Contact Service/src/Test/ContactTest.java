/*
 * Nate Riggs
 * CS-320 - Software Test, Automation QA
 * December 4, 2024
 */


package Test;

import org.junit.jupiter.api.Test;
import Contact.Contact;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    private Contact contact;

    @BeforeEach
    void setUp() {
        contact = new Contact("12345", "Bob", "Smith", "1234567890", "123 Fake St");
    }

    
    @Test
    @DisplayName("Test successful contact creation")
    void testContact() {
        assertEquals("12345", contact.getContactId());
        assertEquals("Bob", contact.getFirstName());
        assertEquals("Smith", contact.getLastName());
        assertEquals("1234567890", contact.getPhoneNumber());
        assertEquals("123 Fake St", contact.getAddress());
    }

    @Test
    @DisplayName("Contact Id must not be longer than 10 characters")
    void testContactIdTooLong() {
        // Ensure an exception is thrown when the Contact ID exceeds 10 characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("01234510123456", "Steve", "Jones", "8281239854", "154 South Ave");
        });
    }

    @Test
    @DisplayName("First Name must not be longer than 10 characters")
    void testFirstNameTooLong() {
        // Ensure an exception is thrown when the first name exceeds 10 characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123456", "Constantinos", "Smith", "8281239854", "154 South Ave");
        });
    }

    @Test
    @DisplayName("Last Name must not be longer than 10 characters")
    void testLastNameTooLong() {
        // Ensure an exception is thrown when the last name exceeds 10 characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123456", "Steve", "AbraKadabraAlakazam", "8281239854", "154 South Ave");
        });
    }
    
    @Test
    @DisplayName("Phone number must not be shorter than 10 digits")
    void testPhoneNumberTooShort() {
        // Ensure an exception is thrown when the phone number is shorter than 10 digits
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123456", "Steve", "Jones", "828124", "154 South Ave");
        });
    }
    
    @Test
    @DisplayName("Phone number must not be longer than 10 digits")
    void testPhoneNumberTooLong() {
        // Ensure an exception is thrown when the phone number is longer than 10 digits
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123456", "Steve", "Jones", "123456789828124", "154 South Ave");
        });
    }
    
    
    @Test
    @DisplayName("Address must be no longer than 30 characters")
    void testAddressTooLong() {
        // Ensure an exception is thrown when the address exceeds 30 characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123456", "Steve", "Jones", "8281239854", "101530 SouthWest Walnut Boulevard South");
        });
    }

    @Test
    @DisplayName("Set address with valid input")
    void testSetAddressValid() {
        String newAddress = "456 Alice Brown Street";
        contact.setAddress(newAddress);
        assertEquals(newAddress, contact.getAddress());
    }

    @Test
    @DisplayName("Set address with invalid input (too long)")
    void testSetAddressInvalid() {
        String invalidAddress = "123456789012345678901234567890123";
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress(invalidAddress));
    }

    @Test
    @DisplayName("Set first name with valid input")
    void testSetFirstNameValid() {
        String newFirstName = "Alice";
        contact.setFirstName(newFirstName);
        assertEquals(newFirstName, contact.getFirstName());
    }

    @Test
    @DisplayName("Set first name with invalid input (too long)")
    void testSetFirstNameInvalid() {
        String invalidFirstName = "Aliceisnotalongfirstnamebutthisoneis";
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName(invalidFirstName));
    }

    @Test
    @DisplayName("Set last name with valid input")
    void testSetLastNameValid() {
        String newLastName = "Brown";
        contact.setLastName(newLastName);
        assertEquals(newLastName, contact.getLastName());
    }

    @Test
    @DisplayName("Set last name with invalid input (too long)")
    void testSetLastNameInvalid() {
        String invalidLastName = "Brownisnotalonglastnamebutthisoneis";
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName(invalidLastName));
    }

    @Test
    @DisplayName("Set phone number with valid input")
    void testSetPhoneNumberValid() {
        String newPhoneNumber = "1234567890";
        contact.setPhoneNumber(newPhoneNumber);
        assertEquals(newPhoneNumber, contact.getPhoneNumber());
    }

    @Test
    @DisplayName("Set phone number with invalid input (too short)")
    void testSetPhoneNumberTooShort() {
        String invalidPhoneNumber = "12345";
        assertThrows(IllegalArgumentException.class, () -> contact.setPhoneNumber(invalidPhoneNumber));
    }

    @Test
    @DisplayName("Set phone number with invalid input (too long)")
    void testSetPhoneNumberNonNumeric() {
        String invalidPhoneNumber = "12345678912345679";
        assertThrows(IllegalArgumentException.class, () -> contact.setPhoneNumber(invalidPhoneNumber));
    }
}
