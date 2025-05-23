package gdg.festa.application.usecase.notices;

import gdg.festa.application.dto.Notice.EditNoticeResponseDto;
import gdg.festa.core.annotation.UseCase;
import gdg.festa.presentation.request.CreateNoticesRequestDto;

@UseCase
public interface EditNoticesUsecase {
    void execute(Long noticeId,CreateNoticesRequestDto createNoticesRequestDto);
}
