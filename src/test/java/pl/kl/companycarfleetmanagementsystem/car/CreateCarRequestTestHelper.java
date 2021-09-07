package pl.kl.companycarfleetmanagementsystem.car;

public class CreateCarRequestTestHelper {

    public static CreateCarRequest provideCreateCarRequest() {
        return CreateCarRequest.builder()
                .brand("Audi")
                .model("A4 Avant")
                .registrationNumber("PP5527P")
                .productionYear(2018)
                .vinNumber("WAUZZZF47JA126506")
                .build();
    }
}
