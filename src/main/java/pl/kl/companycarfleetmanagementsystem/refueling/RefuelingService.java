package pl.kl.companycarfleetmanagementsystem.refueling;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kl.companycarfleetmanagementsystem.trip.Trip;
import pl.kl.companycarfleetmanagementsystem.trip.TripService;
import pl.kl.companycarfleetmanagementsystem.validator.RefuelingDateValidator;
import pl.kl.companycarfleetmanagementsystem.validator.RefuelingMeterStatusValidator;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RefuelingService {

    private final TripService tripService;
    private final RefuelingRepository refuelingRepository;

    public Refueling createRefueling(CreateRefuelingRequest request) {

        final Trip trip = tripService.fetchTripById(request.getTripId());

        RefuelingDateValidator.validateRefuelingDate(request.getDate(), trip.getDepartureDate(), trip.getReturnDate());
        RefuelingMeterStatusValidator.validateRefuelingMeterStatus(request.getMeterStatus(), trip.getDepartureMeterStatus(), trip.getReturnMeterStatus());

        final Refueling refueling = Refueling.builder()
                .date(request.getDate())
                .meterStatus(request.getMeterStatus())
                .fuelAmount(request.getFuelAmount())
                .trip(trip)
                .build();

        return refuelingRepository.save(refueling);
    }

    public List<Refueling> fetchAllRefuelings() {

        return refuelingRepository.findAll();
    }

    public Refueling editRefueling(UpdateRefuelingRequest request) {
        final Refueling refueling = refuelingRepository.findById(request.getId())
                .orElseThrow(() -> new NoSuchElementException("Refueling with id: " + request.getId() + " not found"));

        final Trip trip = tripService.fetchTripById(request.getTripId());

        RefuelingDateValidator.validateRefuelingDate(request.getDate(), trip.getDepartureDate(), trip.getReturnDate());
        RefuelingMeterStatusValidator.validateRefuelingMeterStatus(request.getMeterStatus(), trip.getDepartureMeterStatus(), trip.getReturnMeterStatus());

        refueling.setDate(request.getDate());
        refueling.setMeterStatus(request.getMeterStatus());
        refueling.setFuelAmount(request.getFuelAmount());
        refueling.setTrip(trip);

        return refuelingRepository.save(refueling);
    }
}
