package gdg.festa.application.usecase.reserve;

import gdg.festa.core.annotation.UseCase;
import gdg.festa.presentation.request.reserve.CompletedReserveRequestDto;
import java.util.UUID;

@UseCase
public interface CompleteReserveUseCase {
    Boolean execute(UUID adminId, CompletedReserveRequestDto completedReserveRequestDto);
}
