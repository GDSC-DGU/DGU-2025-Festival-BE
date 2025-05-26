package gdg.festa.application.usecase.sms;

import gdg.festa.core.annotation.UseCase;
import gdg.festa.domain.entity.Reserve;
import gdg.festa.presentation.request.sms.SmsVerifyRequestDto;

@UseCase
public interface SmsVertifyUseCase {
    Reserve execute(SmsVerifyRequestDto smsVerifyRequestDto);
}
