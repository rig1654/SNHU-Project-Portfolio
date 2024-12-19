/*
 * Nate Riggs
 * CS-320 - Software Test, Automation QA
 * December 4, 2024
 */



package Contact;

//The contact service shall be able to delete contacts per contact ID.
//The contact service shall be able to update contact fields per contact ID. The following fields are updatable:
//firstName
//lastName
//Number
//Address
import java.util.ArrayList;

public class ContactService {
	
	
	// Create unique Contact Id when new contact gets created. Gets incremented when new contact gets created
	int currentId = 0;
	
	public static ArrayList<Contact> contactList = new ArrayList<Contact>();
	
	
	
	public void addContact(String firstName, String lastName, String phoneNumber, String address) {
		
		// Convert currentId to string to create new contact
		String idToString = Integer.toString(currentId);
		
		
		// Create new contact
		Contact newContact = new Contact(idToString, firstName, lastName, phoneNumber, address);
		
		// Add new contact to contact list
		contactList.add(newContact);
		
		// Increment contact Id
		++currentId;
		
	}
	
	// Method to delete contact based on contact ID
	public void deleteContact(String contactId) {
	    // Convert contactId to integer
	    int intId = Integer.valueOf(contactId);
	    
	    // Iterate over the list
	    for (int i = 0; i < ContactService.contactList.size(); i++) {
	        if (ContactService.contactList.get(i).getContactId().equals(String.valueOf(intId))) {
	            ContactService.contactList.remove(i);
	            i--;  // Adjust the index after removal to check the next element at this position
	        }
	    }
	}
	
	// Method to update First name based on contactID
	public void updateFirstName(String contactId, String firstName) {
	    for (Contact i : contactList) {
	        if (i.getContactId().equals(contactId)) {
	            i.setFirstName(firstName);
	        }
	    }
	}
	
	// Method to update Last Name based on contactID
	public void updateLastName(String contactId, String lastName) {
	    for (Contact i : contactList) {
	        if (i.getContactId().equals(contactId)) {
	            i.setLastName(lastName);
	        }
	    }
	}
	
	// Method to update phone number based on contactID
	public void updatePhoneNumber(String contactId, String phoneNumber) {
		for (Contact i : contactList) {
			if (i.getContactId().equals(contactId)) {
				i.setPhoneNumber(phoneNumber);
			}
		}
	}
	
	// Method to update address based on contactID
	public void updateAddress(String contactId, String address) {
		for (Contact i : contactList) {
			if (i.getContactId().equals(contactId)) {
				i.setAddress(address);
			}
		}
	}
}
