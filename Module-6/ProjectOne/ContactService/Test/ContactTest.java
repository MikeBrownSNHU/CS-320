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

    @Test
    void constructorAcceptsValidDataAtLimits() {
        String tenCharName = "XyZ9876543";     // 10 chars
        String tenCharId  = "ID77777777";      // 10 chars
        String phone      = "1234567890";      // 10 digits
        String addr30     = "12345678901234567890ABCDE123"; // 30 chars

        Contact c = new Contact(tenCharId, tenCharName, tenCharName, phone, addr30);

        assertEquals(tenCharId, c.getContactId());
        assertEquals(tenCharName, c.getFirstName());
        assertEquals(tenCharName, c.getLastName());
        assertEquals(phone, c.getPhone());
        assertEquals(addr30, c.getAddress());
    }

    @Test
    void idCannotBeNullOrTooLong() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(null, "Alan", "Way", "3332221111", "Maple Rd")
        );

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("ABCDEFGHIJK", "Alan", "Way", "3332221111", "Maple Rd") // 11 chars
        );
    }

    @Test
    void namesMustFollowLengthRules() {
        String longName = "SuperLongNameHere"; // > 10

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("CID1", longName, "Brown", "7775559999", "Grove Ct")
        );

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("CID2", "Ava", longName, "7775559999", "Grove Ct")
        );

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("CID3", null, "Stone", "7775559999", "Grove Ct")
        );
    }

    @Test
    void phoneRequiresExactTenDigits() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("ZZ99", "Jay", "Cole", "99999", "Ridge Lane")
        );

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("ZZ98", "Jay", "Cole", "abcdefghij", "Ridge Lane")
        );
    }

    @Test
    void addressRulesAreEnforced() {
        String tooLongAddress = "12345678901234567890123456789012"; // 32 chars

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("A12", "Meg", "Hart", "2224446666", null)
        );

        assertThrows(IllegalArgumentException.class, () ->
                new Contact("A13", "Meg", "Hart", "2224446666", tooLongAddress)
        );
    }

    @Test
    void settersApplyValidationAsWell() {
        Contact c = new Contact("A1", "Mia", "Frost", "3332221100", "17 Willow Rd");

        c.setFirstName("Luz");
        c.setLastName("Gray");
        c.setPhone("1112223333");
        c.setAddress("808 Orchard Blvd");

        assertEquals("Luz", c.getFirstName());
        assertEquals("Gray", c.getLastName());
        assertEquals("1112223333", c.getPhone());
        assertEquals("808 Orchard Blvd", c.getAddress());
    }

    @Test
    void settersRejectInvalidValues() {
        Contact c = new Contact("TT7", "Ari", "West", "8587779900", "55 Birch Dr");

        assertThrows(IllegalArgumentException.class, () -> c.setPhone("123"));
        assertThrows(IllegalArgumentException.class, () -> c.setFirstName("TwelveLetters"));
        assertThrows(IllegalArgumentException.class, () -> c.setAddress(null));
    }
}
