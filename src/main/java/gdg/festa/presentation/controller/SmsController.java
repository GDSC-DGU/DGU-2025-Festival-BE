package gdg.festa.presentation.controller;

import gdg.festa.application.usecase.sms.SmsCertifyUseCase;
import gdg.festa.application.usecase.sms.SmsVertifyUseCase;
import gdg.festa.core.common.CommonResponseDto;
import gdg.festa.presentation.request.SmsCertifyRequestDto;
import gdg.festa.presentation.request.SmsVerifyRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
@RequiredArgsConstructor
public class SmsController {
    private final SmsCertifyUseCase smsCertifyUseCase;
    private final SmsVertifyUseCase smsVertifyUseCase;

    @PostMapping("/verify")
    public CommonResponseDto<?> verify(
            @RequestBody SmsVerifyRequestDto smsVerifyRequestDto
    ) {
        return CommonResponseDto.ok(smsVertifyUseCase.execute(smsVerifyRequestDto));
    }

    @PostMapping("/certify")
    public CommonResponseDto<?> certify(
            @RequestBody SmsCertifyRequestDto smsCertifyRequestDto
    ) {
        return CommonResponseDto.ok(smsCertifyUseCase.execute(smsCertifyRequestDto));
    }
}
