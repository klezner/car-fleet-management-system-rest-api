package pl.kl.companycarfleetmanagementsystem.refueling;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kl.companycarfleetmanagementsystem.trip.Trip;
import pl.kl.companycarfleetmanagementsystem.trip.TripService;
import pl.kl.companycarfleetmanagementsystem.validator.DateValidator;
import pl.kl.companycarfleetmanagementsystem.validator.MeterStatusValidator;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RefuelingService {

    private final TripService tripService;
    private final RefuelingRepository refuelingRepository;

    public Refueling createRefueling(CreateRefuelingRequest request) {

        final Trip trip = tripService.fetchTripById(request.getTripId());

        DateValidator.validateDateForTripDates(request.getDate(), trip.getDepartureDate(), trip.getReturnDate());
        MeterStatusValidator.validateMeterStatusForTripDates(request.getMeterStatus(), trip.getDepartureMeterStatus(), trip.getReturnMeterStatus());

        final Refueling refueling = Refueling.builder()
                .date(request.getDate())
                .meterStatus(request.getMeterStatus())
                .fuelAmount(request.getFuelAmount())
                .refuelingCost(request.getRefuelingCost())
                .trip(trip)
                .build();

        return refuelingRepository.save(refueling);
    }

    public List<Refueling> fetchAllRefuelings() {

        return refuelingRepository.findAll();
    }

    @Transactional
    public Refueling editRefueling(UpdateRefuelingRequest request) {
        final Refueling refueling = refuelingRepository.findById(request.getId())
                .orElseThrow(() -> new NoSuchElementException("Refueling with id: " + request.getId() + " not found"));

        final Trip trip = tripService.fetchTripById(request.getTripId());

        DateValidator.validateDateForTripDates(request.getDate(), trip.getDepartureDate(), trip.getReturnDate());
        MeterStatusValidator.validateMeterStatusForTripDates(request.getMeterStatus(), trip.getDepartureMeterStatus(), trip.getReturnMeterStatus());

        refueling.setDate(request.getDate());
        refueling.setMeterStatus(request.getMeterStatus());
        refueling.setFuelAmount(request.getFuelAmount());
        refueling.setRefuelingCost(request.getRefuelingCost());
        refueling.setTrip(trip);

        return refuelingRepository.save(refueling);
    }

    public Refueling fetchRefuelingById(Long id) {

        return refuelingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Refueling with id: " + id + " not found"));
    }
}
