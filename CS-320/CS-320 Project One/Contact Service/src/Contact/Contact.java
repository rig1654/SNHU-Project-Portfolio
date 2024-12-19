/*
 * Nate Riggs
 * CS-320 - Software Test, Automation QA
 * December 4, 2024
 */

package Contact;

public class Contact {
    
    private String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        // Validate Contact fields
        validateContactId(contactId);
        validateFirstName(firstName);
        validateLastName(lastName);
        validatePhoneNumber(phone);
        validateAddress(address);
        
        // Assign attributes based on parameters passed into constructor
        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    // Validates the Contact ID to ensure it is not null and doesn't exceed 10 characters
    private void validateContactId(String contactId) {
        if (contactId == null || contactId.length() > 10) {
            throw new IllegalArgumentException("Invalid Contact ID: " + contactId);
        }
    }

    // Validates the first name to ensure it is not null and doesn't exceed 10 characters
    private void validateFirstName(String firstName) {
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("Invalid first name: " + firstName);
        }
    }

    // Validates the last name to ensure it is not null and doesn't exceed 10 characters
    private void validateLastName(String lastName) {
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("Invalid last name: " + lastName);
        }
    }

    // Validates the phone number to ensure it is not null and is exactly 10 characters long
    private void validatePhoneNumber(String phone) {
        if (phone == null || phone.length() != 10) {
            throw new IllegalArgumentException("Invalid phone number: " + phone);
        }
    }

    // Validates the address to ensure it is not null and doesn't exceed 30 characters
    private void validateAddress(String address) {
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Invalid address: " + address);
        }
    }

    // Getter Method for contactID. There is no setter method for the Contact ID
    public String getContactId() {
        return contactId;
    }

    // Getter Method for First Name
    public String getFirstName() {
        return firstName;
    }

    // Setter Method for First Name, with validation
    public void setFirstName(String firstName) {
        validateFirstName(firstName);
        this.firstName = firstName;
    }

    // Getter Method for Last Name
    public String getLastName() {
        return lastName;
    }

    // Setter Method for Last Name, with validation
    public void setLastName(String lastName) {
        validateLastName(lastName);
        this.lastName = lastName;
    }

    // Getter Method for Phone Number
    public String getPhoneNumber() {
        return phone;
    }

    // Setter Method for Phone Number, with validation
    public void setPhoneNumber(String phone) {
        validatePhoneNumber(phone);
        this.phone = phone;
    }

    // Getter Method for address
    public String getAddress() {
        return address;
    }

    // Setter Method for address, with validation
    public void setAddress(String address) {
        validateAddress(address);
        this.address = address;
    }
}
