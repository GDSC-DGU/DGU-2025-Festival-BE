package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.NoticeImages;
import gdg.festa.domain.entity.Notices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeImagesJpaRepository extends JpaRepository<NoticeImages, Long> {
    List<NoticeImages> findByNoticeAndDeletedAtIsNull(Notices notices);

}
