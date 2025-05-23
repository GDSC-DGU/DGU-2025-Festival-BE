package gdg.festa.application.usecase.reserve;

import gdg.festa.core.annotation.UseCase;

@UseCase
public interface UpdateReserveUsecase {
    Boolean execute(String number);

}
