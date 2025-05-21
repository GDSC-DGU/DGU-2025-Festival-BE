package gdg.festa.application.usecase.Losts;

import gdg.festa.core.annotation.UseCase;
import gdg.festa.core.common.CommonResponseDto;
import gdg.festa.presentation.request.CategoryRequestDto;
import gdg.festa.presentation.request.LostsRequestDto;

@UseCase
public interface RegisterLostsUsecase {
    void execute(LostsRequestDto lostsRequestDto, CategoryRequestDto categoryRequestDto);
}
