package gdg.festa.application.service;

import gdg.festa.application.usecase.sms.SmsCertifyUseCase;
import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.core.util.SmsUtil;
import gdg.festa.infrastructure.redis.SmsCertification;
import gdg.festa.presentation.request.SmsCertifyRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SmsCertifyService implements SmsCertifyUseCase {
    private final SmsUtil smsUtil;
    private final SmsCertification smsCertification;

    public Boolean execute(SmsCertifyRequestDto smsCertifyRequestDto) {
        String phone = smsCertifyRequestDto.phoneNumber();
        String code = smsUtil.sendSMS(phone);;
        if(code.isEmpty()){
            throw new CustomException(ErrorCode.SMS_SEND_FAIL);
        }
        smsCertification.createSmsCertification(phone, code);

        return true;
    }
}
