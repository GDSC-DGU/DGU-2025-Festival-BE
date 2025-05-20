package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.FestaAdmins;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FestaAdminsJpaRepository extends JpaRepository<FestaAdmins, UUID> {
    Optional<FestaAdmins> findByLoginId(String loginId);
}
