package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.Lost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LostJpaRepository extends JpaRepository<Lost,Long> {
    Lost save(Lost lost);
    Optional<Lost> findById(Long lostId);
    void deleteById(Long lostId);
}
