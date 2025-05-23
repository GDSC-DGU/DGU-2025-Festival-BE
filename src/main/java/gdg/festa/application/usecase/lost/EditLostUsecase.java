package gdg.festa.application.usecase.lost;

import gdg.festa.core.annotation.UseCase;
import gdg.festa.presentation.request.lost.LostRequestDto;

@UseCase
public interface EditLostUsecase {
    void execute(Long lostId, LostRequestDto lostRequestDto);
}

