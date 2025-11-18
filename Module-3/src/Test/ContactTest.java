package Test;


import Contact.Contact;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Unit tests for Contact class.
 * Each test validates one requirement.
 */
public class ContactTest {

    private Contact contact;

    @BeforeEach
    void setUp() {
        contact = new Contact("12345", "John", "Smith", "1234567890", "100 Main Street");
    }

    @Test
    void contactIsCreatedSuccessfully() {
        assertEquals("12345", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Smith", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("100 Main Street", contact.getAddress());
    }

    @Test
    void contactIdCannotBeNullOrTooLong() {
        assertThrows(IllegalArgumentException.class, () -> new Contact(null, "John", "Smith", "1234567890", "100 Main Street"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("12345678901", "John", "Smith", "1234567890", "100 Main Street"));
    }

    @Test
    void firstAndLastNameValidation() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("123", null, "Smith", "1234567890", "100 Main Street"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("123", "John", null, "1234567890", "100 Main Street"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("123", "TooLongName", "Smith", "1234567890", "100 Main Street"));
    }

    @Test
    void phoneMustBeExactlyTenDigits() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "John", "Smith", "12345", "100 Main Street"));
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "John", "Smith", "abcdefghij", "100 Main Street"));
    }

    @Test
    void addressValidationWorks() {
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "John", "Smith", "1234567890", null));
        String longAddress = "1234567890123456789012345678901";
        assertThrows(IllegalArgumentException.class, () -> new Contact("1", "John", "Smith", "1234567890", longAddress));
    }

    @Test
    void fieldsCanBeUpdatedWithValidData() {
        contact.setFirstName("Jane");
        contact.setLastName("Doe");
        contact.setPhone("0987654321");
        contact.setAddress("200 Oak Lane");
        assertEquals("Jane", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("0987654321", contact.getPhone());
        assertEquals("200 Oak Lane", contact.getAddress());
    }
}
