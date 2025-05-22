package gdg.festa.application.usecase.Losts;

import gdg.festa.application.dto.Losts.GetLostsResponseDto;
import gdg.festa.core.annotation.UseCase;
import gdg.festa.presentation.request.losts.LostsRequestDto;

@UseCase
public interface GetLostsUsecase {
    public GetLostsResponseDto execute(Long lostsId);
}