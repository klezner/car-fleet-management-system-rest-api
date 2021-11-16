package pl.kl.carfleetmanagementsystem.refueling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefuelingRepository extends JpaRepository<Refueling, Long> {

    List<Refueling> getAllByTripId(Long id);
}
