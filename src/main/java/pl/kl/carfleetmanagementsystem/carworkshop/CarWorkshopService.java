package pl.kl.carfleetmanagementsystem.carworkshop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CarWorkshopService {

    private final CarWorkshopRepository carWorkshopRepository;

    public CarWorkshop createCarWorkshop(CreateCarWorkshopRequest request) {

        final CarWorkshop carWorkshop = CarWorkshop.builder()
                .name(request.getName())
                .zipCode(request.getZipCode())
                .city(request.getCity())
                .street(request.getStreet())
                .number(request.getNumber())
                .build();

        return carWorkshopRepository.save(carWorkshop);
    }

    public List<CarWorkshop> fetchAllCarWorkshops() {

        return carWorkshopRepository.findAll();
    }

    @Transactional
    public CarWorkshop editCarWorkshop(UpdateCarWorkshopRequest request) {
        final CarWorkshop carWorkshop = carWorkshopRepository.findById(request.getId())
                .orElseThrow(() -> new NoSuchElementException("Car workshop with id: " + request.getId() + " not found"));

        carWorkshop.setName(request.getName());
        carWorkshop.setZipCode(request.getZipCode());
        carWorkshop.setCity(request.getCity());
        carWorkshop.setStreet(request.getStreet());
        carWorkshop.setNumber(request.getNumber());

        return carWorkshopRepository.save(carWorkshop);
    }

    public CarWorkshop fetchCarWorkshopById(Long id) {

        return carWorkshopRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Car workshop with id: " + id + " not found"));
    }
}
