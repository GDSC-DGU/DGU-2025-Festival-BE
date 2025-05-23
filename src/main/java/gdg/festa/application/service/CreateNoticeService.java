package gdg.festa.application.service;

import gdg.festa.application.usecase.notice.CreateNoticeUsecase;
import gdg.festa.core.util.S3Util;
import gdg.festa.domain.entity.NoticeImage;
import gdg.festa.domain.entity.Notice;
import gdg.festa.domain.repository.NoticeImageRepository;
import gdg.festa.domain.repository.NoticeRepository;
import gdg.festa.presentation.request.notice.CreateNoticesRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateNoticeService implements CreateNoticeUsecase {

    private final S3Util s3Util;
    private final NoticeRepository noticeRepository;
    private final NoticeImageRepository noticeImageRepository;

    public Boolean execute(CreateNoticesRequestDto createNoticesRequestDto) {

        List<String> imageUrls = s3Util.upload(createNoticesRequestDto.images());
        // imageUrl을 DB에 저장 등 추가 로직

        Notice notice = Notice.noticeBuilder()
                .title(createNoticesRequestDto.title())
                .note(createNoticesRequestDto.description())
                .build();

        noticeRepository.save(notice);

        List<NoticeImage> noticeImages = imageUrls.stream()
                .map(imageUrl -> NoticeImage.builder()
                        .notice(notice)
                        .imageUrl(imageUrl)
                        .build())
                .collect(Collectors.toList());

        noticeImageRepository.saveAll(noticeImages);

        return true;
    }

}
