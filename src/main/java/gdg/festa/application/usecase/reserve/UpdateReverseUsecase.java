package gdg.festa.application.usecase.reserve;

import gdg.festa.application.dto.reserve.ReadReserveStateDto;
import gdg.festa.core.annotation.UseCase;
import gdg.festa.domain.type.ReserveStatus;

@UseCase
public interface UpdateReverseUsecase {
    Boolean execute(String number);

}
