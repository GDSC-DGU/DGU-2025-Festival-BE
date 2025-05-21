package gdg.festa.application.usecase.sms;

import gdg.festa.core.annotation.UseCase;
import gdg.festa.presentation.request.sms.SmsCertifyRequestDto;

@UseCase
public interface SmsCertifyUseCase {
    Boolean execute(SmsCertifyRequestDto smsCertifyRequestDto);
}
