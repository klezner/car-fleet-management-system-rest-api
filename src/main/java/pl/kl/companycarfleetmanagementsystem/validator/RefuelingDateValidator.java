package pl.kl.companycarfleetmanagementsystem.validator;

import pl.kl.companycarfleetmanagementsystem.exceptions.RefuelingDateException;

import java.time.LocalDate;

public class RefuelingDateValidator {

    public static boolean validateRefuelingDate(LocalDate refuelingDate, LocalDate departureDate, LocalDate returnDate) {
        if (refuelingDate.isBefore(departureDate) || refuelingDate.isAfter(returnDate)) {
            throw new RefuelingDateException("Incorrect refueling date. Refueling date should be between: " + departureDate + " and " + returnDate);
        }
        return true;
    }
}
