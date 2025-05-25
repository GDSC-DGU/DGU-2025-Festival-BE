package gdg.festa.application.mapper;

import gdg.festa.application.dto.lost.GetLostResponseDto;
import gdg.festa.domain.entity.Lost;
import gdg.festa.presentation.request.lost.CreateLostRequestDto;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class LostMapper {

    public Lost toEntity(CreateLostRequestDto createLostRequestDto) {
        return Lost.LostBuilder()
                .title(createLostRequestDto.title())
                .color(createLostRequestDto.color())
                .brand(createLostRequestDto.brand())
                .location(createLostRequestDto.location())
                .note(createLostRequestDto.note())
                .tag(createLostRequestDto.tag())
                .category(createLostRequestDto.category())
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
