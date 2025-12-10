package Test;

import Appointment.Appointment;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Verifies Appointment validation using varied and boundary-specific data.
 */
public class AppointmentTest {

    private Date tomorrow() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    private Date yesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    @Test
    void acceptsValidDataAtLimits() {
        String id10 = "APPT_99999"; // 10 characters

        String desc50 = "General meeting scheduled for later this week";

        Appointment appointment = new Appointment(id10, tomorrow(), desc50);

        assertEquals(id10, appointment.getAppointmentId());
        assertEquals(desc50, appointment.getDescription());

        Date apptDate = appointment.getAppointmentDate();
        assertNotNull(apptDate);
        assertFalse(apptDate.before(new Date()));
    }

    @Test
    void idCannotBeNullOrTooLong() {
        assertThrows(IllegalArgumentException.class, () ->
                new Appointment(null, tomorrow(), "Regular sync")
        );

        String longId = "ABCDEFGHIJK"; // 11 chars
        assertThrows(IllegalArgumentException.class, () ->
                new Appointment(longId, tomorrow(), "Regular sync")
        );
    }

    @Test
    void dateCannotBeNullOrInThePast() {
        assertThrows(IllegalArgumentException.class, () ->
                new Appointment("D123", null, "No date provided")
        );

        assertThrows(IllegalArgumentException.class, () ->
                new Appointment("D124", yesterday(), "This should not be allowed")
        );
    }

    @Test
    void descriptionMustFollowLengthRules() {
        String over50 = "123456789012345678901234567890123456789012345678901"; // 51 chars

        assertThrows(IllegalArgumentException.class, () ->
                new Appointment("E100", tomorrow(), over50)
        );

        assertThrows(IllegalArgumentException.class, () ->
                new Appointment("E101", tomorrow(), null)
        );
    }

    @Test
    void getterReturnsCopyOfDate() {
        Appointment appointment = new Appointment("SAFE1", tomorrow(), "Check immutability of date");
        Date first = appointment.getAppointmentDate();
        Date modified = new Date(first.getTime() + 86_400_000L); // add 1 day

        // Mutating our local Date should not affect the internal one
        first.setTime(modified.getTime());

        Date second = appointment.getAppointmentDate();
        assertNotEquals(first, second);
    }
    
    @Test
    void validAppointmentIdPassesValidation() {
        Appointment a = new Appointment("A1", tomorrow(), "Simple appointment");
        assertEquals("A1", a.getAppointmentId());
    }
}
