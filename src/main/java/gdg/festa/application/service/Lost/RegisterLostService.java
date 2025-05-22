package gdg.festa.application.service.Lost;

import gdg.festa.application.mapper.LostImageMapper;
import gdg.festa.application.mapper.LostMapper;
import gdg.festa.application.usecase.lost.RegistLostUsecase;
import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.core.util.S3Util;
import gdg.festa.domain.entity.LostImage;
import gdg.festa.domain.entity.Lost;
import gdg.festa.domain.repository.LostImageRepository;
import gdg.festa.domain.repository.LostRepository;
import gdg.festa.presentation.request.lost.LostRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterLostService implements RegistLostUsecase {
    private final LostRepository lostRepository;
    private final LostImageRepository lostImageRepository;
    private final LostMapper lostMapper;
    private final LostImageMapper lostImageMapper;
    private final S3Util s3Util;

    @Override
    public void execute(LostRequestDto lostRequestDto) {

        List<String> imageUrls = s3Util.upload(lostRequestDto.images());

        Lost savelost;
        Lost lost = lostMapper.toEntity(lostRequestDto);
        try{
            savelost = lostRepository.save(lost);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NOT_SAVE_PROPER);
        }

        List<LostImage> lostImages = imageUrls.stream()
                .map(imageUrl -> {
                    return lostImageMapper.toEntity(imageUrl, savelost);
                }).collect(Collectors.toList());

        lostImageRepository.saveAll(lostImages);
    }

}
