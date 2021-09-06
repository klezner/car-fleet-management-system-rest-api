package pl.kl.companycarfleetmanagementsystem.validator;

import pl.kl.companycarfleetmanagementsystem.exceptions.TripDateException;

import java.time.LocalDate;

public class TripDateValidator {

    private static final LocalDate MINIMUM_DATE = LocalDate.of(2000, 1, 1);

    public static boolean validateTripDate(LocalDate departureDate, LocalDate returnDate) {

        validateDepartureDate(departureDate);
        validateReturnDate(departureDate, returnDate);

        return true;
    }

    private static boolean validateDepartureDate(LocalDate departureDate) {
        if (!departureDate.isAfter(MINIMUM_DATE)) {
            throw new TripDateException("Incorrect departure date. Departure date should be after: " + MINIMUM_DATE.toString());
        }
        return departureDate.isAfter(MINIMUM_DATE);
    }

    private static boolean validateReturnDate(LocalDate departureDate, LocalDate returnDate) {
        if (returnDate.isBefore(departureDate)) {
            throw new TripDateException("Incorrect return date. Return date should be after: " + departureDate.toString());
        }
        return departureDate.isBefore(departureDate);
    }
}
