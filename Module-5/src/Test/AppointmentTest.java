package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import Appointment.Appointment;

public class AppointmentTest {

    private Date getFutureDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    private Date getPastDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    @Test
    void testValidAppointmentCreation() {
        Date futureDate = getFutureDate();
        Appointment appt = new Appointment("12345", futureDate, "Dentist appointment");

        assertEquals("12345", appt.getAppointmentId());
        assertEquals(futureDate, appt.getAppointmentDate());
        assertEquals("Dentist appointment", appt.getDescription());
    }

    @Test
    void testAppointmentIdCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(null, getFutureDate(), "Test description");
        });
    }

    @Test
    void testAppointmentIdTooLong() {
        // 11 characters
        String longId = "12345678901";
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(longId, getFutureDate(), "Test description");
        });
    }

    @Test
    void testAppointmentDateCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("123", null, "Test description");
        });
    }

    @Test
    void testAppointmentDateCannotBeInPast() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("123", getPastDate(), "Test description");
        });
    }

    @Test
    void testDescriptionCannotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("123", getFutureDate(), null);
        });
    }

    @Test
    void testDescriptionTooLong() {
        // 51 characters
        String longDescription = "abcdefghijklmnopqrstuvwxyzabcdefgmnopqrstuvwxyzz";
        assertTrue(longDescription.length() > 50);

        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("123", getFutureDate(), longDescription);
        });
    }

    @Test
    void testUpdateAppointmentDateAndDescription() {
        Appointment appt = new Appointment("123", getFutureDate(), "Original description");

        Date newFutureDate = getFutureDate();
        appt.setAppointmentDate(newFutureDate);
        appt.setDescription("Updated description");

        assertEquals(newFutureDate, appt.getAppointmentDate());
        assertEquals("Updated description", appt.getDescription());
    }

    @Test
    void testAppointmentIdIsNotUpdatable() {
        Appointment appt = new Appointment("123", getFutureDate(), "Desc");

        // There is no setter; just confirm the ID stays the same
        assertEquals("123", appt.getAppointmentId());
    }
}
