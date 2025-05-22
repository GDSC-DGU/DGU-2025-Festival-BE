package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.PubAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PubsAdminJpaRepository extends JpaRepository<PubAdmin, UUID> {

    Optional<PubAdmin> findById(UUID id);
    Optional<PubAdmin> findByLoginId(String loginId);
}
