package gdg.festa.infrastructure.sms;

import lombok.Builder;

@Builder
public record SendSmsRequestDto(
        String token_key,
        String msg_type,
        String dest_phone,
        String send_phone,
        String msg_body
) {
}
