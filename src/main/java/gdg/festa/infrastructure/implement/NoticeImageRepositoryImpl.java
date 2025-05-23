package gdg.festa.infrastructure.implement;

import gdg.festa.domain.entity.NoticeImage;
import gdg.festa.domain.entity.Notice;
import gdg.festa.domain.repository.NoticeImageRepository;
import gdg.festa.infrastructure.jpa.NoticeImageJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeImageRepositoryImpl implements NoticeImageRepository {

    private final NoticeImageJpaRepository noticeImageJpaRepository;

    @Override
    public void save(NoticeImage noticeImage) {
        noticeImageJpaRepository.save(noticeImage);
    }

    @Override
    public void saveAll(List<NoticeImage> noticeImage) {
        noticeImageJpaRepository.saveAll(noticeImage);
    }

    @Override
    public List<NoticeImage> findByNoticeAndDeletedAtIsNull(Notice notice) { return noticeImageJpaRepository.findByNoticeAndDeletedAtIsNull(notice); }

}