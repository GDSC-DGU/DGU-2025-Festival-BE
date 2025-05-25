package gdg.festa.presentation.request.notice;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record UpdateNoticeRequestDto(
        Long noticeId, // 수정할 공지사항 id
        String title, // 수정할 공지사항 제목
        String description, // 수정할 공지사항 내용
        List<MultipartFile> images, // 추가할 이미지
        List<String> deleteUrls // 삭제할 이미지 url
) {
    public UpdateNoticeRequestDto {
        if (images == null) {
            images = List.of(); // 불변 빈 리스트로 초기화
        }
        if (deleteUrls == null) {
            deleteUrls = List.of(); // 불변 빈 리스트로 초기화
        }
    }
}
