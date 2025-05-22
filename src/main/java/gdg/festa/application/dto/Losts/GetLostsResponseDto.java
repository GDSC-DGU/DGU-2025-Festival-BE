package gdg.festa.application.dto.Losts;

import gdg.festa.domain.type.TagStatus;

import java.util.List;

public record GetLostsResponseDto(
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
