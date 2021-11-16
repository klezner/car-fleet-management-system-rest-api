package pl.kl.carfleetmanagementsystem.car;

public class UpdateCarRequestTestHelper {

    static UpdateCarRequest provideUpdateCar1Request(Long createdCarId) {
        return UpdateCarRequest.builder()
                .id(createdCarId)
                .brand("Audi")
                .model("A4 Avant")
                .registrationNumber("PO99BA5")
                .productionYear(2018)
                .vinNumber("WAUZZZF47JA126506")
                .build();
    }
}
