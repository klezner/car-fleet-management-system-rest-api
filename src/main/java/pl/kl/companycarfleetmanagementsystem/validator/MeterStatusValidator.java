package pl.kl.companycarfleetmanagementsystem.validator;

import pl.kl.companycarfleetmanagementsystem.exceptions.MeterStatusException;

public class MeterStatusValidator {

    private static final Integer MINIMUM_METER_STATUS = 0;

    public static boolean validateMeterStatus(Integer departureMeterStatus, Integer returnMeterStatus) {

        validateDepartureMeterStatus(departureMeterStatus);
        validateReturnMeterStatus(departureMeterStatus, returnMeterStatus);

        return true;
    }

    private static boolean validateDepartureMeterStatus(Integer departureMeterStatus) {
        if (departureMeterStatus < MINIMUM_METER_STATUS) {
            throw new MeterStatusException("Incorrect departure meter status. Departure meter status should be greater or equal: " + MINIMUM_METER_STATUS);
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
