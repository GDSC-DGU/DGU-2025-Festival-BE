package gdg.festa.application.mapper;

import gdg.festa.application.dto.Losts.GetLostsResponseDto;
import gdg.festa.domain.entity.Lost;
import gdg.festa.presentation.request.losts.LostsRequestDto;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class LostsMapper {

    public Lost toEntity(LostsRequestDto lostsRequestDto) {
        return Lost.LostsBuilder()
                .title(lostsRequestDto.title())
                .color(lostsRequestDto.color())
                .brand(lostsRequestDto.brand())
                .location(lostsRequestDto.location())
                .note(lostsRequestDto.note())
                .tag(lostsRequestDto.tag())
                .category(lostsRequestDto.category())
                .build();
    }

    public GetLostsResponseDto toDto(Lost lost){
        return new GetLostsResponseDto(
                lost.getTitle(),
                lost.getColor(),
                lost.getBrand(),
                lost.getLocation(),
                lost.getNote(),
                lost.getTag(),
                lost.getCategory(),
                Collections.emptyList()
        );
    }


}
