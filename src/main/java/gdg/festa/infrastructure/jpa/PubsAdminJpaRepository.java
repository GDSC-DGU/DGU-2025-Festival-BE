package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.PubsAdmin;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PubsAdminJpaRepository extends JpaRepository<PubsAdmin, UUID> {
    Optional<PubsAdmin> findByLoginId(String loginId);
}
