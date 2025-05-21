package gdg.festa.application.mapper;

import gdg.festa.domain.entity.Losts;
import gdg.festa.presentation.request.LostsRequestDto;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
public class LostsMapper {

    public Losts toEntity(LostsRequestDto lostsRequestDto) {
        return Losts.builder()
                .title(lostsRequestDto.title())
                .color(lostsRequestDto.color())
                .brand(lostsRequestDto.brand())
                .location(lostsRequestDto.location())
                .note(lostsRequestDto.note())
                .tag(lostsRequestDto.tag())
                .build();
    }


}
