package gdg.festa.application.service.Lost;

import gdg.festa.application.usecase.lost.UpdateLostUsecase;
import gdg.festa.application.usecase.notice.UpdateNoticeUsecase;
import gdg.festa.core.util.S3Util;
import gdg.festa.domain.entity.*;
import gdg.festa.domain.repository.LostImageRepository;
import gdg.festa.domain.repository.LostRepository;
import gdg.festa.domain.repository.NoticeImageRepository;
import gdg.festa.domain.repository.NoticeRepository;
import gdg.festa.presentation.request.lost.UpdateLostRequestDto;
import gdg.festa.presentation.request.notice.UpdateNoticeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateLostService implements UpdateLostUsecase {

    private final LostRepository lostRepository;
    private final LostImageRepository lostImageRepository;
    private final S3Util s3Util;

    @Override
    public Boolean execute(UpdateLostRequestDto updateLostRequestDto){
        Lost getLost = lostRepository.findById(updateLostRequestDto.lostId());

        List<LostImage> getLostImages = lostImageRepository.findByLostAndDeletedAtIsNull(getLost);


        for (String deleteUrl : updateLostRequestDto.deleteUrls()) {
            getLostImages.stream()
                    .filter(image -> image.getImageUrl().equals(deleteUrl))
                    .forEach(BaseEntity::delete);
        }
        getLost.setLost(updateLostRequestDto);

        List<String> newImageUrls = s3Util.upload(updateLostRequestDto.images());

        List<LostImage> newImageEntities = newImageUrls.stream()
                .map(url -> LostImage.LostImageBuilder()
                        .lost(getLost)
                        .imageUrl(url)
                        .build()
                )
                .toList();

        lostImageRepository.saveAll(newImageEntities);

        return true;
    }

}
