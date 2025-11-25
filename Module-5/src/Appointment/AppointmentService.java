
package Appointment;

import java.util.HashMap;
import java.util.Map;

public class AppointmentService {

    private final Map<String, Appointment> appointments = new HashMap<>();

    /**
     * Add a appointment. Appointment ID must be unique.
     */
    public void addAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment must not be null.");
        }

        String id = appointment.getAppointmentId();
        if (appointments.containsKey(id)) {
            throw new IllegalArgumentException("Appointment ID must be unique.");
        }

        appointments.put(id, appointment);
    }

    /**
     * Delete an appointment by ID. If the ID does not exist, nothing happens.
     */
    public void deleteAppointment(String appointmentId) {
        appointments.remove(appointmentId);
    }

    /**
     * Helper method for testing / internal use to retrieve an appointment.
     */
    public Appointment getAppointment(String appointmentId) {
        return appointments.get(appointmentId);
    }
}
