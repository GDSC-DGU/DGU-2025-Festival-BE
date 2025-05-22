package gdg.festa.application.usecase.Losts;

import gdg.festa.core.annotation.UseCase;
import gdg.festa.presentation.request.losts.LostsRequestDto;

@UseCase
public interface EditLostsUsecase {
    void execute(Long lostsId, LostsRequestDto lostsRequestDto);
}

