package gdg.festa.application.usecase.lost;

import gdg.festa.application.dto.lost.GetLostResponseDto;
import gdg.festa.core.annotation.UseCase;

@UseCase
public interface GetLostUsecase {
    GetLostResponseDto execute(Long lostId);
}