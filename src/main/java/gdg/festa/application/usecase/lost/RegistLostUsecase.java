package gdg.festa.application.usecase.lost;

import gdg.festa.core.annotation.UseCase;
import gdg.festa.presentation.request.lost.CreateLostRequestDto;

@UseCase
public interface RegistLostUsecase {
    void execute(CreateLostRequestDto createLostRequestDto);
}
