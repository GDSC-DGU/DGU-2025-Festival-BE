package gdg.festa.application.service.Losts;

import gdg.festa.application.mapper.LostsImageMapper;
import gdg.festa.application.mapper.LostsMapper;
import gdg.festa.application.usecase.Losts.RegistLostsUsecase;
import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.core.util.S3Util;
import gdg.festa.domain.entity.LostImages;
import gdg.festa.domain.entity.Losts;
import gdg.festa.domain.repository.LostImageRepository;
import gdg.festa.domain.repository.LostsRepository;
import gdg.festa.presentation.request.losts.LostsRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterLostsService implements RegistLostsUsecase {
    private final LostsRepository lostsRepository;
    private final LostImageRepository lostImageRepository;
    private final LostsMapper lostsMapper;
    private final LostsImageMapper lostsImageMapper;
    private final S3Util s3Util;

    @Override
    public void execute(LostsRequestDto lostsRequestDto) {

        List<String> imageUrls = s3Util.upload(lostsRequestDto.images());

        Losts savelosts;
        Losts losts = lostsMapper.toEntity(lostsRequestDto);
        try{
            savelosts = lostsRepository.save(losts);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NOT_SAVE_PROPER);
        }

        List<LostImages> lostImages = imageUrls.stream()
                .map(imageUrl -> {
                    LostImages img = lostsImageMapper.toEntity(imageUrl, savelosts);
                    return img;
                }).collect(Collectors.toList());

        lostImageRepository.saveAll(lostImages);
    }

}
