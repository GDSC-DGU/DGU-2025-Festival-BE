package gdg.festa.application.usecase.notice;

import gdg.festa.core.annotation.UseCase;
import gdg.festa.presentation.request.notice.UpdateNoticeRequestDto;

@UseCase
public interface UpdateNoticeUsecase {
    Boolean execute(UpdateNoticeRequestDto updateNoticeRequestDto);
}
