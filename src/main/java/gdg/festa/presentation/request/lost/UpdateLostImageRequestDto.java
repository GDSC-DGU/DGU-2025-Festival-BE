package gdg.festa.presentation.request.lost;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public record UpdateLostImageRequestDto(
        Long lostId,
        List<MultipartFile> images
        ) {
public UpdateLostImageRequestDto {
        if (images == null) {
        images = List.of(); // 불변 빈 리스트로 초기화
        }
    }
}

