package gdg.festa.presentation.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record CreateNoticesRequestDto(
        String title,
        String description,
        List<MultipartFile> images
        ) {
        public CreateNoticesRequestDto {
                if (images == null) {
                        images = List.of(); // 불변 빈 리스트로 초기화
                }
        }
}
