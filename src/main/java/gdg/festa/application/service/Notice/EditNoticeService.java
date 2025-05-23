package gdg.festa.application.service.Notice;

import gdg.festa.application.usecase.notices.EditNoticesUsecase;
import gdg.festa.domain.entity.NoticeImages;
import gdg.festa.domain.entity.Notices;
import gdg.festa.domain.repository.NoticeImagesRepository;
import gdg.festa.domain.repository.NoticesRepository;
import gdg.festa.presentation.request.CreateNoticesRequestDto;
import lombok.RequiredArgsConstructor;
import gdg.festa.core.util.S3Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EditNoticeService implements EditNoticesUsecase {

    private final NoticesRepository noticesRepository;
    private final NoticeImagesRepository noticeImagesRepository;
    private final S3Util s3Util;

    @Override
    public void execute(Long noticeId, CreateNoticesRequestDto createNoticesRequestDto){
        Notices getNotices = noticesRepository.findById(noticeId);
        List<NoticeImages> getNoticeImages = noticeImagesRepository.findByNoticeAndDeletedAtIsNull(getNotices);
        getNoticeImages.forEach(image -> {
            image.delete(); // deletedAt = now()
            s3Util.delete(image.getImageUrl()); // S3에서도 실제 삭제
        });

        getNotices.setNotice(
                createNoticesRequestDto.title(),
                createNoticesRequestDto.description()
        );

        List<String> newImageUrls = s3Util.upload(createNoticesRequestDto.images());

        List<NoticeImages> newImageEntities = newImageUrls.stream()
                .map(url -> NoticeImages.noticeImagesBuilder()
                        .notice(getNotices)
                        .imageUrl(url)
                        .build()
                )
                .toList();

        noticeImagesRepository.saveAll(newImageEntities);
    }
}
