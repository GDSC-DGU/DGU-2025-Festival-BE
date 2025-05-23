package gdg.festa.application.usecase.notice;

import gdg.festa.core.annotation.UseCase;
import gdg.festa.presentation.request.notice.CreateNoticeRequestDto;

@UseCase
public interface EditNoticeUsecase {
    void execute(Long noticeId, CreateNoticeRequestDto createNoticeRequestDto);
}
