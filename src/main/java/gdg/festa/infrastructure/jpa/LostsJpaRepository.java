package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.Losts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LostsJpaRepository extends JpaRepository<Losts,Long> {
    Losts save(Losts losts);
}
