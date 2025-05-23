package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.NoticeImage;
import gdg.festa.domain.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeImageJpaRepository extends JpaRepository<NoticeImage, Long> {
    List<NoticeImage> findByNoticeAndDeletedAtIsNull(Notice notice);

}
