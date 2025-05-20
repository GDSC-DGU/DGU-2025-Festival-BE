package gdg.festa.application.usecase.sms;

import gdg.festa.core.annotation.UseCase;
import gdg.festa.presentation.request.SmsVerifyRequestDto;

@UseCase
public interface SmsVertifyUseCase {
    Boolean execute(SmsVerifyRequestDto smsVerifyRequestDto);
}
