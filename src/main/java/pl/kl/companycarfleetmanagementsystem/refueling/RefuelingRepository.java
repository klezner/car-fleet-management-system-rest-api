package pl.kl.companycarfleetmanagementsystem.refueling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefuelingRepository extends JpaRepository<Refueling, Long> {
}
