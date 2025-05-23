package gdg.festa.application.usecase.notice;

import gdg.festa.core.annotation.UseCase;
import gdg.festa.presentation.request.notice.CreateNoticesRequestDto;

@UseCase
public interface CreateNoticeUsecase {
    Boolean execute(CreateNoticesRequestDto createNoticesRequestDto);
}
