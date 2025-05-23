package gdg.festa.application.dto.Notice;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record EditNoticeResponseDto(
        String title,
        String description,
        List<String> images
) {
}
