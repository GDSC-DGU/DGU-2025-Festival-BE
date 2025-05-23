package gdg.festa.application.usecase.reserve;

import gdg.festa.core.annotation.UseCase;
import gdg.festa.presentation.request.reserve.CompletedReserveRequestDto;

@UseCase
public interface CalledReserveUseCase {
    Boolean execute(CompletedReserveRequestDto completedReserveRequestDto);
}
