package gdg.festa.presentation.request;

public record SmsVerifyRequestDto(
        String phoneNumber,
        String certificationNumber
) {
}
