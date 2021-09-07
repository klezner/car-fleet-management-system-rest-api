package pl.kl.companycarfleetmanagementsystem.car;

public class CreateCarRequestTestHelper {

    static CreateCarRequest provideCreateCar2Request() {
        return CreateCarRequest.builder()
                .brand("Citroen")
                .model("DS4")
                .registrationNumber("WX5974A")
                .productionYear(2016)
                .vinNumber("VF7NXAHRMFY570817")
                .build();
    }

    static CreateCarRequest provideCreateCar1Request() {
        return CreateCarRequest.builder()
                .brand("Audi")
                .model("A4 Avant")
                .registrationNumber("PP5527P")
                .productionYear(2018)
                .vinNumber("WAUZZZF47JA126506")
                .build();
    }
}
