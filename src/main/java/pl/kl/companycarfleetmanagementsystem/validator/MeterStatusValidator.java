package pl.kl.companycarfleetmanagementsystem.validator;

import pl.kl.companycarfleetmanagementsystem.exceptions.MeterStatusException;

public class MeterStatusValidator {

    public static boolean validateMeterStatusOnTripCreate(Integer departureMeterStatus, Integer returnMeterStatus, Integer lastReturnMeterStatus) {

        validateDepartureMeterStatus(departureMeterStatus, lastReturnMeterStatus);
        validateReturnMeterStatus(departureMeterStatus, returnMeterStatus);

        return true;
    }

    public static boolean validateMeterStatusOnTripEdit(Integer departureMeterStatus, Integer returnMeterStatus) {

        validateReturnMeterStatus(departureMeterStatus, returnMeterStatus);

        return true;
    }

    private static boolean validateDepartureMeterStatus(Integer departureMeterStatus, Integer lastReturnMeterStatus) {
        if (departureMeterStatus < lastReturnMeterStatus) {
            throw new MeterStatusException("Incorrect departure meter status. Departure meter status should be greater or equal: " + lastReturnMeterStatus);
        }
        return true;
    }

    private static boolean validateReturnMeterStatus(Integer departureMeterStatus, Integer returnMeterStatus) {
        if (returnMeterStatus < departureMeterStatus) {
            throw new MeterStatusException("Incorrect return meter status. Return meter status should be less or equal to: " + returnMeterStatus);
        }
        return true;
    }
}
