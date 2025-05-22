package gdg.festa.presentation.request.losts;

import gdg.festa.domain.type.TagStatus;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record LostsRequestDto(
        String title,
        String color,
        String brand,
        String location,
        String note,
        TagStatus tag,
        String category,
        List<MultipartFile> images
) {
    public LostsRequestDto {
        if (images == null) {
            images = List.of(); // 불변 빈 리스트로 초기화
        }
    }
}
