package pl.kl.companycarfleetmanagementsystem.carworkshop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarWorkshopRepository extends JpaRepository<CarWorkshop, Long> {
}
