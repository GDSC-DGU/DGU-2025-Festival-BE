package gdg.festa.application.mapper;

import gdg.festa.application.dto.Losts.GetLostsResponseDto;
import gdg.festa.domain.entity.LostImages;
import gdg.festa.domain.entity.Losts;
import gdg.festa.presentation.request.losts.LostsRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LostsMapper {

    public Losts toEntity(LostsRequestDto lostsRequestDto) {
        return Losts.LostsBuilder()
                .title(lostsRequestDto.title())
                .color(lostsRequestDto.color())
                .brand(lostsRequestDto.brand())
                .location(lostsRequestDto.location())
                .note(lostsRequestDto.note())
                .tag(lostsRequestDto.tag())
                .category(lostsRequestDto.category())
                .build();
    }

    public GetLostsResponseDto toDto(Losts losts){
        return new GetLostsResponseDto(
                losts.getTitle(),
                losts.getColor(),
                losts.getBrand(),
                losts.getLocation(),
                losts.getNote(),
                losts.getTag(),
                losts.getCategory(),
                Collections.emptyList()
        );
    }


}
