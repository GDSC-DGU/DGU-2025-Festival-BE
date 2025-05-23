package gdg.festa.application.service.Notice;

import gdg.festa.application.usecase.notices.CreateNoticesUsecase;
import gdg.festa.core.util.S3Util;
import gdg.festa.domain.entity.NoticeImages;
import gdg.festa.domain.entity.Notices;
import gdg.festa.domain.repository.NoticeImagesRepository;
import gdg.festa.domain.repository.NoticesRepository;
import gdg.festa.presentation.request.CreateNoticesRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateNoticesService implements CreateNoticesUsecase {

    private final S3Util s3Util;
    private final NoticesRepository noticesRepository;
    private final NoticeImagesRepository noticeImagesRepository;

    public Boolean execute(CreateNoticesRequestDto createNoticesRequestDto) {

        List<String> imageUrls = s3Util.upload(createNoticesRequestDto.images());

        Notices notices = Notices.noticeBuilder()
                .title(createNoticesRequestDto.title())
                .note(createNoticesRequestDto.description())
                .build();

        noticesRepository.save(notices);

        List<NoticeImages> noticeImages = imageUrls.stream()
                .map(imageUrl -> NoticeImages.noticeImagesBuilder()
                        .notice(notices)
                        .imageUrl(imageUrl)
                        .build())
                .collect(Collectors.toList());

        noticeImagesRepository.saveAll(noticeImages);

        return true;
    }

}