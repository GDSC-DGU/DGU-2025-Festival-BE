package gdg.festa.application.usecase.Losts;

import gdg.festa.core.annotation.UseCase;

@UseCase
public interface RemoveLostsUsecase {
    void execute(Long LostsId);
}
