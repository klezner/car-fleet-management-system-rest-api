package pl.kl.companycarfleetmanagementsystem.repair;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kl.companycarfleetmanagementsystem.trip.Trip;
import pl.kl.companycarfleetmanagementsystem.trip.TripService;
import pl.kl.companycarfleetmanagementsystem.validator.DateValidator;
import pl.kl.companycarfleetmanagementsystem.validator.MeterStatusValidator;

@Service
@RequiredArgsConstructor
public class RepairService {

    private final TripService tripService;
    private final RepairRepository repairRepository;

    public Repair createRepair(CreateRepairRequest request) {

        final Trip trip = tripService.fetchTripById(request.getTripId());

        DateValidator.validateLeftAndPickupDatesForRepair(request.getLeftDate(), request.getPickupDate());
        DateValidator.validateDateForTripDates(request.getLeftDate(), trip.getDepartureDate(), trip.getReturnDate());
        DateValidator.validateDateForTripDates(request.getPickupDate(), trip.getDepartureDate(), trip.getReturnDate());
        MeterStatusValidator.validateMeterStatusForTripDates(request.getLeftMeterStatus(), trip.getDepartureMeterStatus(), trip.getReturnMeterStatus());

        final Repair repair = Repair.builder()
                .leftDate(request.getLeftDate())
                .leftMeterStatus(request.getLeftMeterStatus())
                .invoiceNumber(request.getInvoiceNumber())
                .invoiceDate(request.getInvoiceDate())
                .repairCost(request.getRepairCost())
                .pickupDate(request.getPickupDate())
                .trip(trip)
                .build();

        return repairRepository.save(repair);
    }
}
