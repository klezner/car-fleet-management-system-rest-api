package pl.kl.companycarfleetmanagementsystem.carworkshop;

import org.springframework.stereotype.Component;

@Component
public class CarWorkshopMapper {

    public CarWorkshopResponse mapCarWorkshopToCarWorkshopResponse(CarWorkshop carWorkshop) {

        return CarWorkshopResponse.builder()
                .id(carWorkshop.getId())
                .name(carWorkshop.getName())
                .zipCode(carWorkshop.getZipCode())
                .city(carWorkshop.getCity())
                .street(carWorkshop.getStreet())
                .number(carWorkshop.getNumber())
                .build();
    }
}
