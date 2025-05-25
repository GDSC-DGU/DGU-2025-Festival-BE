package gdg.festa.application.service.notice;

import gdg.festa.application.usecase.notice.RemoveNoticeUsecase;
import gdg.festa.core.constant.Constants;
import gdg.festa.core.util.RedisUtil;
import gdg.festa.domain.entity.NoticeImage;
import gdg.festa.domain.entity.Notice;
import gdg.festa.domain.repository.NoticeImageRepository;
import gdg.festa.domain.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RemoveNoticeService implements RemoveNoticeUsecase {

    private final NoticeRepository noticeRepository;
    private final NoticeImageRepository noticeImageRepository;
    private final RedisUtil redisUtil;

    @PostConstruct
    public void init() {
        redisUtil.setKeyPrefix(Constants.REDIS_NOTICE_KEY_PREFIX);
    }

    @Override
    public void execute(Long noticeId){
        Notice getNotice = noticeRepository.findById(noticeId);

        List<NoticeImage> noticeImages = noticeImageRepository.findByNoticeAndDeletedAtIsNull(getNotice);
        for (NoticeImage image : noticeImages){
            image.delete();
        }
        getNotice.delete();

        resetNoticeCache(getNotice);
    }

    private void resetNoticeCache(Notice notice) {
        redisUtil.delete(String.valueOf(notice.getNoticeId()));
        redisUtil.delete("all");
        log.info("Notice cache reset for ID: {}", notice.getNoticeId());
    }
}
