package gdg.festa.application.service.Lost;

import gdg.festa.application.mapper.LostImageMapper;
import gdg.festa.application.usecase.lost.EditLostUsecase;
import gdg.festa.core.util.S3Util;
import gdg.festa.domain.entity.LostImage;
import gdg.festa.domain.entity.Lost;
import gdg.festa.domain.repository.LostImageRepository;
import gdg.festa.domain.repository.LostRepository;
import gdg.festa.presentation.request.lost.LostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EditLostService implements EditLostUsecase {

    private final LostRepository lostRepository;
    private final LostImageRepository lostImageRepository;
    private final LostImageMapper lostImageMapper;
    private final S3Util s3Util;

    @Override
    public void execute(Long lostId, LostRequestDto lostRequestDto) {
        Lost lost = lostRepository.findById(lostId);

        lost.setLost(lostRequestDto);

        List<LostImage> oldImages = lostImageRepository.findByLostAndDeletedAtIsNull(lost);
        oldImages.forEach(img -> {
            img.delete();  // deletedAt = now()

        });

        List<String> imageUrls = s3Util.upload(lostRequestDto.images());

        List<LostImage> newImageEntities = imageUrls.stream()
                .map(url -> lostImageMapper.toEntity(url, lost))
                .collect(Collectors.toList());

        lostImageRepository.saveAll(newImageEntities);
    }
}
