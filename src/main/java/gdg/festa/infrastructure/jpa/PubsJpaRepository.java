package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.Pubs;
import gdg.festa.domain.entity.Reserves;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PubsJpaRepository extends JpaRepository<Pubs, Long> {

    Optional<Pubs> findById(Long id);
    List<Pubs> findAll();
}
