package gdg.festa.presentation.request.lost;

import gdg.festa.domain.type.TagStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record UpdateLostRequestDto(
        Long lostId,
        String title,
        String color,
        String brand,
        String location,
        String note,
        TagStatus tag,
        String category,
        List<MultipartFile> images, // 추가할 이미지
        List<String> deleteUrls // 삭제할 이미지 url
) {
    public UpdateLostRequestDto {
        if (images == null) {
            images = List.of(); // 불변 빈 리스트로 초기화
        }
        if (deleteUrls == null) {
            deleteUrls = List.of(); // 불변 빈 리스트로 초기화
        }
    }
}