package pl.kl.companycarfleetmanagementsystem.validator;

import pl.kl.companycarfleetmanagementsystem.exceptions.TripDateException;

import java.time.LocalDate;

public class TripDateValidator {

    public static boolean validateTripDateOnTripCreate(LocalDate departureDate, LocalDate returnDate, LocalDate lastReturnDate) {

        validateDepartureDate(departureDate, lastReturnDate);
        validateReturnDate(departureDate, returnDate);

        return true;
    }

    public static boolean validateTripDateOnTripEdit(LocalDate departureDate, LocalDate returnDate) {

        validateReturnDate(departureDate, returnDate);

        return true;
    }

    private static boolean validateDepartureDate(LocalDate departureDate, LocalDate lastReturnDate) {
        if (departureDate.isBefore(lastReturnDate)) {
            throw new TripDateException("Incorrect departure date. Departure date should be after: " + lastReturnDate.toString());
        }
        return lastReturnDate.isBefore(departureDate);
    }

    private static boolean validateReturnDate(LocalDate departureDate, LocalDate returnDate) {
        if (returnDate.isBefore(departureDate)) {
            throw new TripDateException("Incorrect return date. Return date should be after: " + departureDate.toString());
        }
        return departureDate.isBefore(returnDate);
    }
}
