package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Appointment.Appointment;
import Appointment.AppointmentService;

public class AppointmentServiceTest {

    private AppointmentService service;

    @BeforeEach
    void setUp() {
        service = new AppointmentService();
    }

    private Date getFutureDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    @Test
    void testAddAppointmentSuccessfully() {
        Appointment appt = new Appointment("A1", getFutureDate(), "Checkup");
        service.addAppointment(appt);

        Appointment stored = service.getAppointment("A1");
        assertNotNull(stored);
        assertEquals("Checkup", stored.getDescription());
    }

    @Test
    void testAddAppointmentWithDuplicateIdThrows() {
        Appointment appt1 = new Appointment("A1", getFutureDate(), "First");
        Appointment appt2 = new Appointment("A1", getFutureDate(), "Second");

        service.addAppointment(appt1);
        assertThrows(IllegalArgumentException.class, () -> service.addAppointment(appt2));
    }

    @Test
    void testDeleteAppointmentRemovesIt() {
        Appointment appt = new Appointment("A2", getFutureDate(), "To delete");
        service.addAppointment(appt);

        assertNotNull(service.getAppointment("A2"));

        service.deleteAppointment("A2");

        assertNull(service.getAppointment("A2"));
    }

    @Test
    void testDeleteNonexistentAppointmentDoesNotThrow() {
        // Should simply do nothing and not throw
        assertDoesNotThrow(() -> service.deleteAppointment("DOES_NOT_EXIST"));
    }

    @Test
    void testAddNullAppointmentThrows() {
        assertThrows(IllegalArgumentException.class, () -> service.addAppointment(null));
    }
}
