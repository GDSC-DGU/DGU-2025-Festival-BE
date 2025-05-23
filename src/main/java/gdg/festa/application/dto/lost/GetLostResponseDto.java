package gdg.festa.application.dto.lost;

import gdg.festa.domain.type.TagStatus;

import java.util.List;

public record GetLostResponseDto(
        String title,
        String color,
        String brand,
        String location,
        String note,
        TagStatus tag,
        String category,
        List<String> images
) {
}
