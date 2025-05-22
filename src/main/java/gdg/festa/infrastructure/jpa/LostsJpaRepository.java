package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.Lost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LostsJpaRepository extends JpaRepository<Lost,Long> {
    Lost save(Lost lost);
    Optional<Lost> findById(Long lostsId);
    void deleteById(Long LostsId);
}
