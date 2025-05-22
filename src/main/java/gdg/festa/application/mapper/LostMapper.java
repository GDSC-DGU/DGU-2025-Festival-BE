package gdg.festa.application.mapper;

import gdg.festa.application.dto.lost.GetLostResponseDto;
import gdg.festa.domain.entity.Lost;
import gdg.festa.presentation.request.lost.LostRequestDto;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class LostMapper {

    public Lost toEntity(LostRequestDto lostRequestDto) {
        return Lost.LostsBuilder()
                .title(lostRequestDto.title())
                .color(lostRequestDto.color())
                .brand(lostRequestDto.brand())
                .location(lostRequestDto.location())
                .note(lostRequestDto.note())
                .tag(lostRequestDto.tag())
                .category(lostRequestDto.category())
                .build();
    }

    public GetLostResponseDto toDto(Lost lost){
        return new GetLostResponseDto(
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
