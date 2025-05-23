package gdg.festa.infrastructure.implement;

import gdg.festa.domain.entity.NoticeImages;
import gdg.festa.domain.entity.Notices;
import gdg.festa.domain.repository.NoticeImagesRepository;
import gdg.festa.infrastructure.jpa.NoticeImagesJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeImagesRepositoryImpl implements NoticeImagesRepository {

    private final NoticeImagesJpaRepository noticeImagesJpaRepository;

    @Override
    public void save(NoticeImages noticeImages) {
        noticeImagesJpaRepository.save(noticeImages);
    }

    @Override
    public void saveAll(List<NoticeImages> noticeImages) {
        noticeImagesJpaRepository.saveAll(noticeImages);
    }

    @Override
    public List<NoticeImages> findByNoticeAndDeletedAtIsNull(Notices notices) { return noticeImagesJpaRepository.findByNoticeAndDeletedAtIsNull(notices); }

}