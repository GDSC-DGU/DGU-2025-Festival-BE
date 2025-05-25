package gdg.festa.presentation.request.notice;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record UpdateNoticeImageRequestDto(
        Long noticeId,
        List<MultipartFile> images
) {
    public UpdateNoticeImageRequestDto {
        if (images == null) {
            images = List.of(); // 불변 빈 리스트로 초기화
        }
    }
}
