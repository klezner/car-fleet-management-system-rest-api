package pl.kl.companycarfleetmanagementsystem.carworkshop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
