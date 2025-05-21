package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.Losts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LostsJpaRepository extends JpaRepository<Losts,Long> {
    Losts save(Losts losts);
    Optional<Losts> findById(Long lostsId);
    void deleteById(Long LostsId);
}
