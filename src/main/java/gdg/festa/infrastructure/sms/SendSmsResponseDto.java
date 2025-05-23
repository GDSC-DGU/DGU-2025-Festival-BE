package gdg.festa.infrastructure.sms;

import java.util.Objects;
import org.springframework.lang.Nullable;

public record SendSmsResponseDto(
        String code,
        Object content,
        String error

) {
}
