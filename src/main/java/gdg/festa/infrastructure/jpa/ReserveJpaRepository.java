package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.Reserves;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReserveJpaRepository extends JpaRepository<Reserves, Long> {
    Reserves findByPhoneNumber(String phoneNumber);
}
