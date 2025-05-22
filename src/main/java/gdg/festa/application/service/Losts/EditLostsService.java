package gdg.festa.application.service.Losts;

import gdg.festa.application.mapper.LostsImageMapper;
import gdg.festa.application.usecase.Losts.EditLostsUsecase;
import gdg.festa.core.util.S3Util;
import gdg.festa.domain.entity.LostImage;
import gdg.festa.domain.entity.Lost;
import gdg.festa.domain.repository.LostImageRepository;
import gdg.festa.domain.repository.LostsRepository;
import gdg.festa.presentation.request.losts.LostsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EditLostsService implements EditLostsUsecase {

    private final LostsRepository lostsRepository;
    private final LostImageRepository lostImageRepository;
    private final LostsImageMapper lostsImageMapper;
    private final S3Util s3Util;

    @Override
    public void execute(Long lostsId, LostsRequestDto lostsRequestDto) {
        Lost lost = lostsRepository.findById(lostsId);

        lost.setLost(lostsRequestDto);

        List<LostImage> oldImages = lostImageRepository.findByLostsAndDeletedAtIsNull(lost);
        oldImages.forEach(img -> {
            img.delete();  // deletedAt = now()

        });

        List<String> imageUrls = s3Util.upload(lostsRequestDto.images());

        List<LostImage> newImageEntities = imageUrls.stream()
                .map(url -> lostsImageMapper.toEntity(url, lost))
                .collect(Collectors.toList());

        lostImageRepository.saveAll(newImageEntities);
    }
}
