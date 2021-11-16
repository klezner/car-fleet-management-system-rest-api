package pl.kl.carfleetmanagementsystem.carworkshop;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CarWorkshopResponse {

    private Long id;
    private String name;
    private String zipCode;
    private String city;
    private String street;
    private String number;
}
