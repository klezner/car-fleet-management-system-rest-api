package pl.kl.companycarfleetmanagementsystem.validator;

import pl.kl.companycarfleetmanagementsystem.exceptions.RefuelingMeterStatusException;

public class RefuelingMeterStatusValidator {

    public static boolean validateRefuelingMeterStatus(Integer refuelingMeterStatus, Integer departureMeterStatus, Integer returnMeterStatus) {

        if (refuelingMeterStatus < departureMeterStatus || refuelingMeterStatus > returnMeterStatus) {
            throw new RefuelingMeterStatusException("Incorrect refueling meter status. Refueling meter status should be between: " + departureMeterStatus + " and " + returnMeterStatus);
        }
        return false;
    }
}
