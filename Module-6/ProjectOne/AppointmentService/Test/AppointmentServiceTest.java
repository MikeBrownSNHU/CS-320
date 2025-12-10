package Test;

import Appointment.Appointment;
import Appointment.AppointmentNotFoundException;
import Appointment.AppointmentService;
import Appointment.DuplicateAppointmentIdException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercises AppointmentService with normal operations and edge cases.
 */
public class AppointmentServiceTest {

    private AppointmentService service;

    @BeforeEach
    void setup() {
        service = new AppointmentService();
    }

    private Date inTwoDays() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 2);
        return cal.getTime();
    }

    @Test
    void canAddAndRetrieveAppointment() {
        Appointment created = service.addAppointment(
                "SLOT1",
                inTwoDays(),
                "Product review with stakeholder group"
        );

        Appointment fetched = service.getAppointment("SLOT1");

        assertNotNull(created);
        assertSame(created, fetched);
        assertEquals("SLOT1", fetched.getAppointmentId());
        assertEquals("Product review with stakeholder group", fetched.getDescription());
    }

    @Test
    void addingDuplicateIdThrowsCustomException() {
        service.addAppointment("UNQ_A1", inTwoDays(), "Morning standup");

        assertThrows(DuplicateAppointmentIdException.class, () ->
                service.addAppointment("UNQ_A1", inTwoDays(), "Clashing meeting")
        );
    }

    @Test
    void deletingExistingAppointmentRemovesIt() {
        service.addAppointment("DEL_A", inTwoDays(), "Cleanup session");

        service.deleteAppointment("DEL_A");

        assertThrows(AppointmentNotFoundException.class, () ->
                service.getAppointment("DEL_A")
        );
    }

    @Test
    void deletingUnknownIdThrowsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () ->
                service.deleteAppointment("NOT_THERE")
        );
    }

    @Test
    void invalidDataStillFailsWhenAddedThroughService() {
        // Description too long, should fail via Appointment constructor
        String over50 = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; // 51 x's

        assertThrows(IllegalArgumentException.class, () ->
                service.addAppointment("BAD1", inTwoDays(), over50)
        );
    }
    
    @Test
    void addingAppointmentWithNullIdIsRejected() {
        assertThrows(IllegalArgumentException.class, () ->
                service.addAppointment(null, inTwoDays(), "Quarterly planning session")
        );
    }
    
    @Test
    void addingAppointmentWithNullIdThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                service.addAppointment(null, inTwoDays(), "Routine check")
        );
    }
    
}
