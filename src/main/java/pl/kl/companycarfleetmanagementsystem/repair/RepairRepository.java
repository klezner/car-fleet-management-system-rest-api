package pl.kl.companycarfleetmanagementsystem.repair;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {

    List<Repair> getAllByCarWorkshopId(Long id);

    List<Repair> getAllByTripId(Long id);
}
