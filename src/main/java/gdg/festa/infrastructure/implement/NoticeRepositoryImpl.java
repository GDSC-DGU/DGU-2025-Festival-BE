package gdg.festa.infrastructure.implement;

import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.domain.entity.Notice;
import gdg.festa.domain.repository.NoticeRepository;
import gdg.festa.infrastructure.jpa.NoticeJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NoticeRepositoryImpl implements NoticeRepository {

    private final NoticeJpaRepository noticeJpaRepository;

    @Override
    public void save(Notice notice) {
        noticeJpaRepository.save(notice);

    }

    @Override
    public Notice findById(Long noticeId){
        return noticeJpaRepository.findById(noticeId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_LOST));
    }
}
