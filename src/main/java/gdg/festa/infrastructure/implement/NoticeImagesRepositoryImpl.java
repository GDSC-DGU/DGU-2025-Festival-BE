package gdg.festa.infrastructure.implement;

import gdg.festa.domain.entity.NoticeImage;
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
    public void save(NoticeImage noticeImage) {
        noticeImagesJpaRepository.save(noticeImage);
    }

    @Override
    public void saveAll(List<NoticeImage> noticeImages) {
        noticeImagesJpaRepository.saveAll(noticeImages);
    }

}
