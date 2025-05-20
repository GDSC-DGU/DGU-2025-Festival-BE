package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.Pubs;
import gdg.festa.domain.entity.PubsAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PubsAdminJpaRepository extends JpaRepository<PubsAdmin, UUID> {

    Optional<PubsAdmin> findById(UUID id);
}
