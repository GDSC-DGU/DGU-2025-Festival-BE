package gdg.festa.application.service.Notice;

import gdg.festa.application.usecase.notices.DeleteNoticesUsecase;
import gdg.festa.application.usecase.notices.RemoveNoticeUsecase;
import gdg.festa.domain.entity.NoticeImages;
import gdg.festa.domain.entity.Notices;
import gdg.festa.domain.repository.NoticeImagesRepository;
import gdg.festa.domain.repository.NoticesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RemoveNoticeService implements RemoveNoticeUsecase {

    private final NoticesRepository noticesRepository;
    private final NoticeImagesRepository noticeImagesRepository;

    @Override
    public void execute(Long noticeId){
        Notices getNotice = noticesRepository.findById(noticeId);

        List<NoticeImages> noticeImages = noticeImagesRepository.findByNoticeAndDeletedAtIsNull(getNotice);
        for (NoticeImages images : noticeImages){
            images.delete();
        }
        getNotice.delete();
    }
}
