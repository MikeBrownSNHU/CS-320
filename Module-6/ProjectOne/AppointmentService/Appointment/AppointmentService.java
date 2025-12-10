
package Appointment;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;


public class AppointmentService {

    private final Map<String, Appointment> appointmentStore = new HashMap<>();

    public Appointment addAppointment(String id, Date date, String description) {
        if (id == null) {
            throw new IllegalArgumentException("Appointment ID cannot be null.");
        }

        if (appointmentStore.containsKey(id)) {
            throw new DuplicateAppointmentIdException("Appointment ID already exists: " + id);
        }

        Appointment appointment = new Appointment(id, date, description);
        appointmentStore.put(id, appointment);
        return appointment;
    }

    public void deleteAppointment(String id) {
        if (!appointmentStore.containsKey(id)) {
            throw new AppointmentNotFoundException("Appointment not found for deletion: " + id);
        }
        appointmentStore.remove(id);
    }

    public Appointment getAppointment(String id) {
        Appointment appointment = appointmentStore.get(id);
        if (appointment == null) {
            throw new AppointmentNotFoundException("Appointment not found: " + id);
        }
        return appointment;
    }
}
