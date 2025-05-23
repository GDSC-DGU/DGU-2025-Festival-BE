package gdg.festa.application.service.notice;

import gdg.festa.application.usecase.notice.CreateNoticeUsecase;
import gdg.festa.core.util.S3Util;
import gdg.festa.domain.entity.NoticeImage;
import gdg.festa.domain.entity.Notice;
import gdg.festa.domain.repository.NoticeImageRepository;
import gdg.festa.domain.repository.NoticeRepository;
import gdg.festa.presentation.request.notice.CreateNoticeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateNoticeService implements CreateNoticeUsecase {

    private final S3Util s3Util;
    private final NoticeRepository noticesRepository;
    private final NoticeImageRepository noticeImagesRepository;

    public Boolean execute(CreateNoticeRequestDto createNoticesRequestDto) {

        List<String> imageUrls = s3Util.upload(createNoticesRequestDto.images());

        Notice notice = Notice.noticeBuilder()
                .title(createNoticesRequestDto.title())
                .note(createNoticesRequestDto.description())
                .build();

        noticesRepository.save(notice);

        List<NoticeImage> noticeImages = imageUrls.stream()
                .map(imageUrl -> NoticeImage.noticeImagesBuilder()
                        .notice(notice)
                        .imageUrl(imageUrl)
                        .build())
                .collect(Collectors.toList());

        noticeImagesRepository.saveAll(noticeImages);

        return true;
    }

}