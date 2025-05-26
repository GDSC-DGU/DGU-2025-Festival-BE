package gdg.festa.application.service.sms;

import gdg.festa.application.mapper.ReserveMapper;
import gdg.festa.application.usecase.sms.SmsVertifyUseCase;
import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.domain.entity.Reserve;
import gdg.festa.domain.repository.ReserveRepository;
import gdg.festa.domain.type.ReserveStatus;
import gdg.festa.infrastructure.redis.SmsCertification;
import gdg.festa.presentation.request.sms.SmsVerifyRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SmsVertifyService implements SmsVertifyUseCase {
    private final ReserveMapper reserveMapper;
    private final ReserveRepository reserveRepository;
    private final SmsCertification smsCertification;

    @Override
    public Reserve execute(SmsVerifyRequestDto smsVerifyRequestDto) {
        if (isVerify(smsVerifyRequestDto)) {
            throw new CustomException(ErrorCode.SMS_VERIFY_FAILED);
        }

        smsCertification.deleteSmsCertification(smsVerifyRequestDto.phoneNumber());

        // 이미 전화번호 인증을 마친 경우
        if (reserveRepository.existsByPhoneNumberAndReserveStatus(
                smsVerifyRequestDto.phoneNumber(), ReserveStatus.ENABLED
        )) {
            return reserveRepository.findByPhoneNumberAndReserveStatusIsEnabled(
                    smsVerifyRequestDto.phoneNumber()
            );
        }

        // 전화번호 인증을 처음 하는 경우
        Reserve reserve = reserveMapper.toEntity(
                smsVerifyRequestDto.phoneNumber(),
                smsVerifyRequestDto.browserToken()
        );

        return reserveRepository.save(reserve);

    }


    public boolean isVerify(SmsVerifyRequestDto smsVerifyRequestDto) {
        return !(smsCertification.hasKey(smsVerifyRequestDto.phoneNumber()) &&
                smsCertification.getSmsCertification(smsVerifyRequestDto.phoneNumber())
                        .equals(smsVerifyRequestDto.certificationNumber()));
    }
}
