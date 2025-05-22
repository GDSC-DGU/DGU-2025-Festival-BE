package gdg.festa.application.usecase.lost;

import gdg.festa.core.annotation.UseCase;

@UseCase
public interface RemoveLostUsecase {
    void execute(Long LostsId);
}
