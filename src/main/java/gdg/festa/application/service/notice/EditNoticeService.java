package gdg.festa.application.service.notice;

import gdg.festa.application.usecase.notice.EditNoticeUsecase;
import gdg.festa.domain.entity.NoticeImage;
import gdg.festa.domain.entity.Notice;
import gdg.festa.domain.repository.NoticeImageRepository;
import gdg.festa.domain.repository.NoticeRepository;
import gdg.festa.presentation.request.notice.CreateNoticeRequestDto;
import lombok.RequiredArgsConstructor;
import gdg.festa.core.util.S3Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EditNoticeService implements EditNoticeUsecase {

    private final NoticeRepository noticeRepository;
    private final NoticeImageRepository noticeImagesRepository;
    private final S3Util s3Util;

    @Override
    public void execute(Long noticeId, CreateNoticeRequestDto createNoticesRequestDto){
        Notice getNotice = noticeRepository.findById(noticeId);
        List<NoticeImage> getNoticeImages = noticeImagesRepository.findByNoticeAndDeletedAtIsNull(getNotice);
        getNoticeImages.forEach(image -> {
            image.delete(); // deletedAt = now()
            s3Util.delete(image.getImageUrl()); // S3에서도 실제 삭제
        });

        getNotice.setNotice(
                createNoticesRequestDto.title(),
                createNoticesRequestDto.description()
        );

        List<String> newImageUrls = s3Util.upload(createNoticesRequestDto.images());

        List<NoticeImage> newImageEntities = newImageUrls.stream()
                .map(url -> NoticeImage.noticeImagesBuilder()
                        .notice(getNotice)
                        .imageUrl(url)
                        .build()
                )
                .toList();

        noticeImagesRepository.saveAll(newImageEntities);
    }
}
