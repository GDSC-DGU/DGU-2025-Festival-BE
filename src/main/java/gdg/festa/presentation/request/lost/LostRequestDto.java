package gdg.festa.presentation.request.lost;

import gdg.festa.domain.type.TagStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record LostRequestDto(
        String title,
        String color,
        String brand,
        String location,
        String note,
        TagStatus tag,
        String category,
        List<MultipartFile> images
) {
    public LostRequestDto {
        if (images == null) {
            images = List.of(); // 불변 빈 리스트로 초기화
        }
    }
}
