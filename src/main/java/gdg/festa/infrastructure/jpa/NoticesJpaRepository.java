package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.Notices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticesJpaRepository extends JpaRepository<Notices, Long> {

}
