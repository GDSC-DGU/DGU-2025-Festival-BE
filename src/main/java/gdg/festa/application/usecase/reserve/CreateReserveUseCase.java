package gdg.festa.application.usecase.reserve;

import gdg.festa.core.annotation.UseCase;
import gdg.festa.presentation.request.reserve.CreateReserveRequestDto;

@UseCase
public interface CreateReserveUseCase {
    Boolean execute(Long boothsId, CreateReserveRequestDto createReserveRequestDto);
}
