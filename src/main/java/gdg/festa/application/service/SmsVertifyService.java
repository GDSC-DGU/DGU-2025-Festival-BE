package gdg.festa.application.service;

import gdg.festa.application.usecase.sms.SmsVertifyUseCase;
import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.infrastructure.redis.SmsCertification;
import gdg.festa.presentation.request.SmsVerifyRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SmsVertifyService implements SmsVertifyUseCase {

    private SmsCertification smsCertification;

    @Override
    public Boolean execute(SmsVerifyRequestDto smsVerifyRequestDto) {
        if (isVerify(smsVerifyRequestDto)) {
            throw new CustomException(ErrorCode.SMS_VERIFY_FAILED);
        }
        smsCertification.deleteSmsCertification(smsVerifyRequestDto.phoneNumber());

        return true;
    }


    private boolean isVerify(SmsVerifyRequestDto smsVerifyRequestDto) {
        return !(smsCertification.hasKey(smsVerifyRequestDto.phoneNumber()) &&
                smsCertification.getSmsCertification(smsVerifyRequestDto.phoneNumber())
                        .equals(smsVerifyRequestDto.certificationNumber()));
    }
}
