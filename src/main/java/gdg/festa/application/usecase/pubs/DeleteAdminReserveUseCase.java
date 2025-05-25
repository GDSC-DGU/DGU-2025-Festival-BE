package gdg.festa.application.usecase.pubs;

import gdg.festa.core.annotation.UseCase;
import gdg.festa.presentation.request.reserve.CompletedReserveRequestDto;
import java.util.UUID;

@UseCase
public interface DeleteAdminReserveUseCase {
    Boolean execute(CompletedReserveRequestDto completedReserveRequestDto, UUID adminId);
}
