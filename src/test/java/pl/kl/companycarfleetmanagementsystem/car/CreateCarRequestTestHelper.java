package pl.kl.companycarfleetmanagementsystem.car;

public class CreateCarRequestTestHelper {

    static CreateCarRequest provideCreateCar1Request() {
        return CreateCarRequest.builder()
                .brand("Audi")
                .model("A4 Avant")
                .registrationNumber("PP5527P")
                .productionYear(2018)
                .vinNumber("WAUZZZF47JA126506")
                .build();
    }

    static CreateCarRequest provideCreateCar2Request() {
        return CreateCarRequest.builder()
                .brand("Citroen")
                .model("DS4")
                .registrationNumber("WX5974A")
                .productionYear(2016)
                .vinNumber("VF7NXAHRMFY570817")
                .build();
    }

    static CreateCarRequest provideCreateCar1WithEmptyBrandRequest() {
        return CreateCarRequest.builder()
                .brand("")
                .model("A4 Avant")
                .registrationNumber("PP5527P")
                .productionYear(2018)
                .vinNumber("WAUZZZF47JA126506")
                .build();
    }

    static CreateCarRequest provideCreateCar1WithNullBrandRequest() {
        return CreateCarRequest.builder()
                .brand(null)
                .model("A4 Avant")
                .registrationNumber("PP5527P")
                .productionYear(2018)
                .vinNumber("WAUZZZF47JA126506")
                .build();
    }

    static CreateCarRequest provideCreateCar1WithEmptyModelRequest() {
        return CreateCarRequest.builder()
                .brand("Audi")
                .model("")
                .registrationNumber("PP5527P")
                .productionYear(2018)
                .vinNumber("WAUZZZF47JA126506")
                .build();
    }

    static CreateCarRequest provideCreateCar1WithNullModelRequest() {
        return CreateCarRequest.builder()
                .brand("Audi")
                .model(null)
                .registrationNumber("PP5527P")
                .productionYear(2018)
                .vinNumber("WAUZZZF47JA126506")
                .build();
    }

    static CreateCarRequest provideCreateCar1WithEmptyRegNumberRequest() {
        return CreateCarRequest.builder()
                .brand("Audi")
                .model("A4 Avant")
                .registrationNumber("")
                .productionYear(2018)
                .vinNumber("WAUZZZF47JA126506")
                .build();
    }

    static CreateCarRequest provideCreateCar1WithNullRegNumberRequest() {
        return CreateCarRequest.builder()
                .brand("Audi")
                .model("A4 Avant")
                .registrationNumber(null)
                .productionYear(2018)
                .vinNumber("WAUZZZF47JA126506")
                .build();
    }

    static CreateCarRequest provideCreateCar1WithRegNumberShorterThanRequiredRequest() {
        return CreateCarRequest.builder()
                .brand("Audi")
                .model("A4 Avant")
                .registrationNumber("PP")
                .productionYear(2018)
                .vinNumber("WAUZZZF47JA126506")
                .build();
    }

    static CreateCarRequest provideCreateCar1WithRegNumberLongerThanRequiredRequest() {
        return CreateCarRequest.builder()
                .brand("Audi")
                .model("A4 Avant")
                .registrationNumber("PP55274567P")
                .productionYear(2018)
                .vinNumber("WAUZZZF47JA126506")
                .build();
    }

    static CreateCarRequest provideCreateCar1WithEmptyVinNumberRequest() {
        return CreateCarRequest.builder()
                .brand("Audi")
                .model("A4 Avant")
                .registrationNumber("PP5527P")
                .productionYear(2018)
                .vinNumber("")
                .build();
    }

    static CreateCarRequest provideCreateCar1WithNullVinNumberRequest() {
        return CreateCarRequest.builder()
                .brand("Audi")
                .model("A4 Avant")
                .registrationNumber("PP5527P")
                .productionYear(2018)
                .vinNumber(null)
                .build();
    }

    static CreateCarRequest provideCreateCar1WithVinNumberShorterThanRequiredRequest() {
        return CreateCarRequest.builder()
                .brand("Audi")
                .model("A4 Avant")
                .registrationNumber("PP5527P")
                .productionYear(2018)
                .vinNumber("WAUZZZF")
                .build();
    }

    static CreateCarRequest provideCreateCar1WithVinNumberLongerThanRequiredRequest() {
        return CreateCarRequest.builder()
                .brand("Audi")
                .model("A4 Avant")
                .registrationNumber("PP5527P")
                .productionYear(2018)
                .vinNumber("WAUZZZF47JA126506356267842")
                .build();
    }

    static CreateCarRequest provideCreateCar1RequestWithNullProdYear() {
        return CreateCarRequest.builder()
                .brand("Audi")
                .model("A4 Avant")
                .registrationNumber("PP5527P")
                .productionYear(null)
                .vinNumber("WAUZZZF47JA126506")
                .build();
    }

    static CreateCarRequest provideCreateCar1RequestWithTooEarlyProdYear() {
        return CreateCarRequest.builder()
                .brand("Audi")
                .model("A4 Avant")
                .registrationNumber("PP5527P")
                .productionYear(1990)
                .vinNumber("WAUZZZF47JA126506")
                .build();
    }

    static CreateCarRequest provideCreateCar1RequestWithTooLateProdYear() {
        return CreateCarRequest.builder()
                .brand("Audi")
                .model("A4 Avant")
                .registrationNumber("PP5527P")
                .productionYear(2100)
                .vinNumber("WAUZZZF47JA126506")
                .build();
    }
}
