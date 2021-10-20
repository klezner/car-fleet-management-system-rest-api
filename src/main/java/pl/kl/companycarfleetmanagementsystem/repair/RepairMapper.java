package pl.kl.companycarfleetmanagementsystem.repair;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kl.companycarfleetmanagementsystem.carworkshop.CarWorkshopMapper;
import pl.kl.companycarfleetmanagementsystem.trip.TripMapper;

@Component
@RequiredArgsConstructor
public class RepairMapper {

    private final CarWorkshopMapper carWorkshopMapper;
    private final TripMapper tripMapper;

    public RepairResponse mapRepairToRepairResponse(Repair repair) {

        return RepairResponse.builder()
                .id(repair.getId())
                .leftDate(repair.getLeftDate())
                .leftMeterStatus(repair.getLeftMeterStatus())
                .invoiceNumber(repair.getInvoiceNumber())
                .invoiceDate(repair.getInvoiceDate())
                .repairCost(repair.getRepairCost())
                .pickupDate(repair.getPickupDate())
                .trip(tripMapper.mapTripToTripResponse(repair.getTrip()))
                .carWorkshop(carWorkshopMapper.mapCarWorkshopToCarWorkshopResponse(repair.getCarWorkshop()))
                .build();
    }
}
