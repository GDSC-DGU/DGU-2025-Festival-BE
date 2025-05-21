package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.Reserves;
import gdg.festa.domain.type.ReserveStatus;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ReserveJpaRepository extends JpaRepository<Reserves, UUID> {
    Optional<Reserves> findByPhoneNumber(String phoneNumber);
    Optional<Reserves> findByPhoneNumberAndReserveStatus(String phoneNumber, ReserveStatus reserveStatus);
}
