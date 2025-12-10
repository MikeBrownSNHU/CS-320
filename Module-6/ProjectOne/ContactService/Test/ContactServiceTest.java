package Test;

import Contact.Contact;
import Contact.ContactNotFoundException;
import Contact.ContactService;
import Contact.DuplicateIdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ContactService.
 * Verifies add, delete, update, and error handling.
 */
public class ContactServiceTest {

    private ContactService service;

    @BeforeEach
    void init() {
        service = new ContactService();
    }

    @Test
    void canInsertContactAndRetrieveIt() {
        service.addContact("C001", "Alex", "Ridge", "5554443322", "42 Walnut Blvd");

        Contact stored = service.getContact("C001");

        assertNotNull(stored);
        assertEquals("Alex", stored.getFirstName());
        assertEquals("Ridge", stored.getLastName());
        assertEquals("5554443322", stored.getPhone());
    }

    @Test
    void addingSameIdTwiceTriggersDuplicateError() {
        service.addContact("ZX9", "Mia", "Lane", "9098887766", "7 Pine Court");

        assertThrows(DuplicateIdException.class, () ->
                service.addContact("ZX9", "Tara", "Jones", "1112223333", "8 Oak Avenue")
        );
    }

    @Test
    void deletingValidRecordRemovesItFromMemory() {
        service.addContact("AB12", "Leo", "Cross", "2127778899", "900 Hillcrest Way");

        service.deleteContact("AB12");

        assertThrows(ContactNotFoundException.class, () -> service.getContact("AB12"));
    }

    @Test
    void deletingUnknownIdThrowsProperException() {
        assertThrows(ContactNotFoundException.class, () -> service.deleteContact("nope-404"));
    }

    @Test
    void updatesReflectProperlyAcrossFields() {
        service.addContact("U77", "Ava", "Knight", "3012228899", "19 Cedar St");

        service.updateFirstName("U77", "Aria");
        service.updateLastName("U77", "Norris");
        service.updatePhone("U77", "9991112233");
        service.updateAddress("U77", "88 Elmwood Dr");

        Contact mod = service.getContact("U77");

        assertAll(
                () -> assertEquals("Aria", mod.getFirstName()),
                () -> assertEquals("Norris", mod.getLastName()),
                () -> assertEquals("9991112233", mod.getPhone()),
                () -> assertEquals("88 Elmwood Dr", mod.getAddress())
        );
    }

    @Test
    void cannotUpdateNonexistentContact() {
        assertThrows(ContactNotFoundException.class, () ->
                service.updateAddress("ghostID", "Nowhere Rd")
        );
    }

    @Test
    void invalidDataBubblesUpFromContactConstructor() {
        // invalid phone (not 10 digits)
        assertThrows(IllegalArgumentException.class, () ->
                service.addContact("K222", "Jill", "Mark", "12", "Somewhere")
        );
    }
    
    @Test
    void addingContactWithNullIdThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                service.addContact(null, "Ava", "Stone", "1234567890", "10 Hill Rd")
        );
    }
}