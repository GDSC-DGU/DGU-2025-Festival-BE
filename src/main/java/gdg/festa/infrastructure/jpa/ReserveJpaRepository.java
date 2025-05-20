package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.Reserves;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ReserveJpaRepository extends JpaRepository<Reserves, Long> {
    Optional<Reserves> findByPhoneNumber(String phoneNumber);
}
