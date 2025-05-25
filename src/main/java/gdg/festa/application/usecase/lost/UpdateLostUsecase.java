package gdg.festa.application.usecase.lost;

import gdg.festa.core.annotation.UseCase;
import gdg.festa.presentation.request.lost.UpdateLostImageRequestDto;
import gdg.festa.presentation.request.lost.UpdateLostRequestDto;
import gdg.festa.presentation.request.notice.UpdateNoticeRequestDto;

@UseCase
public interface UpdateLostUsecase {
    Boolean execute(UpdateLostRequestDto updateLostRequestDto);
}
