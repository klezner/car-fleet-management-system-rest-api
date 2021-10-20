package pl.kl.companycarfleetmanagementsystem.repair;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kl.companycarfleetmanagementsystem.carworkshop.CarWorkshop;
import pl.kl.companycarfleetmanagementsystem.carworkshop.CarWorkshopService;
import pl.kl.companycarfleetmanagementsystem.trip.Trip;
import pl.kl.companycarfleetmanagementsystem.trip.TripService;
import pl.kl.companycarfleetmanagementsystem.validator.DateValidator;
import pl.kl.companycarfleetmanagementsystem.validator.MeterStatusValidator;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RepairService {

    private final CarWorkshopService carWorkshopService;
    private final TripService tripService;
    private final RepairRepository repairRepository;

    public Repair createRepair(CreateRepairRequest request) {

        final Trip trip = tripService.fetchTripById(request.getTripId());
        final CarWorkshop carWorkshop = carWorkshopService.fetchCarWorkshopById(request.getCarWorkshopId());


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
                .carWorkshop(carWorkshop)
                .build();

        return repairRepository.save(repair);
    }

    public List<Repair> fetchAllRepairs() {

        return repairRepository.findAll();
    }

    @Transactional
    public Repair editRepair(UpdateRepairRequest request) {
        final Repair repair = repairRepository.findById(request.getId())
                .orElseThrow(() -> new NoSuchElementException("Repair with id: " + request.getId() + " not found"));

        final Trip trip = tripService.fetchTripById(request.getTripId());
        final CarWorkshop carWorkshop = carWorkshopService.fetchCarWorkshopById(request.getCarWorkshopId());

        DateValidator.validateLeftAndPickupDatesForRepair(request.getLeftDate(), request.getPickupDate());
        DateValidator.validateDateForTripDates(request.getLeftDate(), trip.getDepartureDate(), trip.getReturnDate());
        DateValidator.validateDateForTripDates(request.getPickupDate(), trip.getDepartureDate(), trip.getReturnDate());
        MeterStatusValidator.validateMeterStatusForTripDates(request.getLeftMeterStatus(), trip.getDepartureMeterStatus(), trip.getReturnMeterStatus());

        repair.setLeftDate(request.getLeftDate());
        repair.setLeftMeterStatus(request.getLeftMeterStatus());
        repair.setInvoiceNumber(request.getInvoiceNumber());
        repair.setInvoiceDate(request.getInvoiceDate());
        repair.setRepairCost(request.getRepairCost());
        repair.setPickupDate(request.getPickupDate());
        repair.setTrip(trip);
        repair.setCarWorkshop(carWorkshop);

        return repairRepository.save(repair);
    }

    public Repair fetchRepairById(Long id) {

        return repairRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Repair with id: " + id + " not found"));
    }
}
