package gdg.festa.application.service.Lost;

import gdg.festa.application.dto.lost.GetLostResponseDto;
import gdg.festa.application.mapper.LostMapper;
import gdg.festa.application.usecase.lost.GetLostUsecase;
import gdg.festa.domain.entity.LostImage;
import gdg.festa.domain.entity.Lost;
import gdg.festa.domain.repository.LostImageRepository;
import gdg.festa.domain.repository.LostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GetLostService implements GetLostUsecase {

    private final LostRepository lostRepository;
    private final LostImageRepository lostImageRepository;
    private final LostMapper lostMapper;

    // 이미지만 추가해서 넣으면 된다
    @Override
    public GetLostResponseDto execute(Long lostId){
        Lost getLost = lostRepository.findById(lostId);
        List<LostImage> getLostImages =  lostImageRepository.findByLostId(lostId);

        GetLostResponseDto getLostResponseDto = lostMapper.toDto(getLost);

        List<String> images = getLostImages.stream()
                .map(LostImage::getImageUrl)
                .collect(Collectors.toList());

        return new GetLostResponseDto(
                getLostResponseDto.title(),
                getLostResponseDto.color(),
                getLostResponseDto.brand(),
                getLostResponseDto.location(),
                getLostResponseDto.note(),
                getLostResponseDto.tag(),
                getLostResponseDto.category(),
                images
        );
    }

}
