package gdg.festa.presentation.request.notice;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record CreateNoticeRequestDto(
        String title,
        String description,
        List<MultipartFile> images
        ) {
        public CreateNoticeRequestDto {
                if (images == null) {
                        images = List.of(); // 불변 빈 리스트로 초기화
                }
        }
}
