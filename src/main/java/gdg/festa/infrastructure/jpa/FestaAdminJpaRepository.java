package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.FestaAdmin;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FestaAdminJpaRepository extends JpaRepository<FestaAdmin, UUID> {
    Optional<FestaAdmin> findByLoginId(String loginId);
}
