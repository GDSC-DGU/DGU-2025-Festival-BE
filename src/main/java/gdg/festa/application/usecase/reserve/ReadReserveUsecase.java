package gdg.festa.application.usecase.reserve;

import gdg.festa.application.dto.reserve.ReadReserveStateDto;
import gdg.festa.core.annotation.UseCase;

@UseCase
public interface ReadReserveUsecase {
    ReadReserveStateDto execute(String number);

}
