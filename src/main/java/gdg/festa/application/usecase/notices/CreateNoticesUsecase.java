package gdg.festa.application.usecase.notices;

import gdg.festa.core.annotation.UseCase;
import gdg.festa.presentation.request.CreateNoticesRequestDto;

@UseCase
public interface CreateNoticesUsecase {
    Boolean execute(CreateNoticesRequestDto createNoticesRequestDto);
}
