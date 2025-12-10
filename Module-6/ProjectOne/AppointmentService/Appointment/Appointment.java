
package Appointment;

import java.util.Date;


public class Appointment {

    private final String appointmentId;   // <= 10, not null
    private final Date appointmentDate;   // not null, not in the past
    private final String description;     // <= 50, not null

    public Appointment(String appointmentId, Date appointmentDate, String description) {
        this.appointmentId = validateId(appointmentId);
        this.appointmentDate = validateDate(appointmentDate);
        this.description = validateDescription(description);
    }

    // ----- Getters -----
    public String getAppointmentId() {
        return appointmentId;
    }

    public Date getAppointmentDate() {
        // defensive copy so callers can’t mutate our internal date
        return new Date(appointmentDate.getTime());
    }

    public String getDescription() {
        return description;
    }

    // ----- Validation helpers -----
    private String validateId(String id) {
        if (id == null || id.length() > 10) {
            throw new IllegalArgumentException("Appointment ID must be non-null and at most 10 characters.");
        }
        return id;
    }

    private Date validateDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Appointment date cannot be null.");
        }
        // Requirement: cannot be in the past
        // (using before(new Date()) as SNHU spec suggests)
        if (date.before(new Date())) {
            throw new IllegalArgumentException("Appointment date cannot be in the past.");
        }
        return date;
    }

    private String validateDescription(String description) {
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException("Description must be non-null and at most 50 characters.");
        }
        return description;
    }
}