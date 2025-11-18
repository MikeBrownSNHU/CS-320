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
        if (contactMap.containsKey(id)) {
            throw new IllegalArgumentException("Contact ID must be unique");
        }
        Contact contact = new Contact(id, first, last, phone, address);
        contactMap.put(id, contact);
        return contact;
    }

    public void deleteContact(String id) {
        if (!contactMap.containsKey(id)) {
            throw new IllegalArgumentException("Contact ID not found");
        }
        contactMap.remove(id);
    }

    public void updateFirstName(String id, String newFirst) {
        getContact(id).setFirstName(newFirst);
    }

    public void updateLastName(String id, String newLast) {
        getContact(id).setLastName(newLast);
    }

    public void updatePhone(String id, String newPhone) {
        getContact(id).setPhone(newPhone);
    }

    public void updateAddress(String id, String newAddress) {
        getContact(id).setAddress(newAddress);
    }

    public Contact getContact(String id) {
        Contact contact = contactMap.get(id);
        if (contact == null) {
            throw new IllegalArgumentException("Contact not found: " + id);
        }
        return contact;
    }
}
