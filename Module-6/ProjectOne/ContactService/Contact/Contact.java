/**
 * Contact class represents a single contact entry.
 * Each contact must include a unique, immutable ID
 * and validated personal details.
 */

package Contact;



public class Contact {

    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        this.contactId = validateId(contactId);
        this.firstName = validateName(firstName, 10, "First name");
        this.lastName = validateName(lastName, 10, "Last name");
        this.phone = validatePhone(phone);
        this.address = validateAddress(address);
    }

    // ----- Getters -----
    public String getContactId() { return contactId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }

    // ----- Setters for updatable fields -----
    public void setFirstName(String firstName) {
        this.firstName = validateName(firstName, 10, "First name");
    }

    public void setLastName(String lastName) {
        this.lastName = validateName(lastName, 10, "Last name");
    }

    public void setPhone(String phone) {
        this.phone = validatePhone(phone);
    }

    public void setAddress(String address) {
        this.address = validateAddress(address);
    }

    // ----- Validation helpers -----
    private static String validateId(String id) {
        if (id == null || id.length() > 10) {
            throw new IllegalArgumentException("Invalid contact ID");
        }
        return id;
    }

    private static String validateName(String value, int maxLength, String fieldLabel) {
        if (value == null || value.length() > maxLength) {
            throw new IllegalArgumentException(fieldLabel + " cannot be null or exceed " + maxLength + " characters");
        }
        return value;
    }

    private static String validatePhone(String phone) {
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone number must be exactly 10 digits");
        }
        return phone;
    }

    private static String validateAddress(String address) {
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("Address cannot be null or longer than 30 characters");
        }
        return address;
    }
}
