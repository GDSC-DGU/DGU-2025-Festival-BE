package gdg.festa.application.service.Losts;

import gdg.festa.application.dto.Losts.GetLostsResponseDto;
import gdg.festa.application.mapper.LostsMapper;
import gdg.festa.application.usecase.Losts.GetLostsUsecase;
import gdg.festa.domain.entity.LostImage;
import gdg.festa.domain.entity.Lost;
import gdg.festa.domain.repository.LostImageRepository;
import gdg.festa.domain.repository.LostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GetLostsService implements GetLostsUsecase {

    private final LostsRepository lostsRepository;
    private final LostImageRepository lostImageRepository;
    private final LostsMapper lostsMapper;

    // 이미지만 추가해서 넣으면 된다
    @Override
    public GetLostsResponseDto execute(Long lostsId){
        Lost getLost = lostsRepository.findById(lostsId);
        List<LostImage> getLostImages =  lostImageRepository.findByLostsLostsId(lostsId);

        GetLostsResponseDto getLostsResponseDto = lostsMapper.toDto(getLost);

        List<String> images = getLostImages.stream()
                .map(LostImage::getImageUrl)
                .collect(Collectors.toList());

        return new GetLostsResponseDto(
                getLostsResponseDto.title(),
                getLostsResponseDto.color(),
                getLostsResponseDto.brand(),
                getLostsResponseDto.location(),
                getLostsResponseDto.note(),
                getLostsResponseDto.tag(),
                getLostsResponseDto.category(),
                images
        );
    }

}
