package Appointment;

public class DuplicateAppointmentIdException extends RuntimeException {
    public DuplicateAppointmentIdException(String message) {
        super(message);
    }
}