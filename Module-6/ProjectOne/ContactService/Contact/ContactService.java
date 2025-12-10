package Contact;

import java.util.HashMap;
import java.util.Map;

/**
 * ContactService manages adding, updating,
 * and deleting Contact objects in memory.
 */
public class ContactService {

    private final Map<String, Contact> contactMap = new HashMap<>();

    public Contact addContact(String id, String first, String last, String phone, String address) {
        if (id == null) {
            throw new IllegalArgumentException("Contact ID cannot be null.");
        }

        if (contactMap.containsKey(id)) {
            throw new DuplicateIdException("Contact ID must be unique: " + id);
        }

        // Contact constructor handles validation for the rest of the fields
        Contact contact = new Contact(id, first, last, phone, address);
        contactMap.put(id, contact);
        return contact;
    }

    public void deleteContact(String id) {
        if (!contactMap.containsKey(id)) {
            throw new ContactNotFoundException("Contact ID not found: " + id);
        }
        contactMap.remove(id);
    }

    public void updateFirstName(String id, String newFirst) {
        getExistingContact(id).setFirstName(newFirst);
    }

    public void updateLastName(String id, String newLast) {
        getExistingContact(id).setLastName(newLast);
    }

    public void updatePhone(String id, String newPhone) {
        getExistingContact(id).setPhone(newPhone);
    }

    public void updateAddress(String id, String newAddress) {
        getExistingContact(id).setAddress(newAddress);
    }

    public Contact getContact(String id) {
        return getExistingContact(id);
    }

    // Internal helper to centralize "not found" handling
    private Contact getExistingContact(String id) {
        Contact contact = contactMap.get(id);
        if (contact == null) {
            throw new ContactNotFoundException("Contact not found: " + id);
        }
        return contact;
    }
}
