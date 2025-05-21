package gdg.festa.presentation.request.sms;

public record SmsVerifyRequestDto(
        String phoneNumber,
        String certificationNumber,
        String browserToken
) {
}
