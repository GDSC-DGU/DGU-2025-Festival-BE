package gdg.festa.application.service.notice;

import gdg.festa.application.usecase.notice.RemoveNoticeUsecase;
import gdg.festa.domain.entity.NoticeImage;
import gdg.festa.domain.entity.Notice;
import gdg.festa.domain.repository.NoticeImageRepository;
import gdg.festa.domain.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RemoveNoticeService implements RemoveNoticeUsecase {

    private final NoticeRepository noticeRepository;
    private final NoticeImageRepository noticeImageRepository;

    @Override
    public void execute(Long noticeId){
        Notice getNotice = noticeRepository.findById(noticeId);

        List<NoticeImage> noticeImages = noticeImageRepository.findByNoticeAndDeletedAtIsNull(getNotice);
        for (NoticeImage image : noticeImages){
            image.delete();
        }
        getNotice.delete();
    }
}
