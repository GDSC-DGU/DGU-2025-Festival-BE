package gdg.festa.application.service.notice;

import gdg.festa.application.usecase.notice.UpdateNoticeUsecase;
import gdg.festa.core.util.S3Util;
import gdg.festa.domain.entity.BaseEntity;
import gdg.festa.core.constant.Constants;
import gdg.festa.core.util.RedisUtil;
import gdg.festa.domain.entity.NoticeImage;
import gdg.festa.domain.entity.Notice;
import gdg.festa.domain.repository.NoticeImageRepository;
import gdg.festa.domain.repository.NoticeRepository;
import gdg.festa.presentation.request.notice.UpdateNoticeRequestDto;
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
public class UpdateNoticeService implements UpdateNoticeUsecase {

    private final NoticeRepository noticeRepository;
    private final NoticeImageRepository noticeImagesRepository;
    private final S3Util s3Util;
    private final RedisUtil redisUtil;

    @PostConstruct
    public void init() {
        redisUtil.setKeyPrefix(Constants.REDIS_NOTICE_KEY_PREFIX);
    }

    @Override
    public Boolean execute(UpdateNoticeRequestDto updateNoticeRequestDto){
        Notice getNotice = noticeRepository.findById(updateNoticeRequestDto.noticeId());

        List<NoticeImage> getNoticeImages = noticeImagesRepository.findByNoticeAndDeletedAtIsNull(getNotice);


        for (String deleteUrl : updateNoticeRequestDto.deleteUrls()) {
            getNoticeImages.stream()
                    .filter(image -> image.getImageUrl().equals(deleteUrl))
                    .forEach(BaseEntity::delete);
        }
        getNotice.setNotice(
                updateNoticeRequestDto.title(),
                updateNoticeRequestDto.description()
        );

        List<String> newImageUrls = s3Util.upload(updateNoticeRequestDto.images());

        List<NoticeImage> newImageEntities = newImageUrls.stream()
                .map(url -> NoticeImage.noticeImagesBuilder()
                        .notice(getNotice)
                        .imageUrl(url)
                        .build()
                )
                .toList();

        noticeImagesRepository.saveAll(newImageEntities);

        resetNoticeCache(getNotice);

        return true;
    }

    private void resetNoticeCache(Notice notice) {
        redisUtil.delete(String.valueOf(notice.getNoticeId()));
        redisUtil.delete("all");
        log.info("Lost cache reset for ID: {}", notice.getNoticeId());
    }

}
