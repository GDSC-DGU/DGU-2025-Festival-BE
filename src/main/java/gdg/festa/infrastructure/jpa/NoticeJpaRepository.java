package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeJpaRepository extends JpaRepository<Notice, Long> {

}
