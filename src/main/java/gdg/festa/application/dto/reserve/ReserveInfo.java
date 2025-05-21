package gdg.festa.application.dto.reserve;

import gdg.festa.domain.type.ReserveStatus;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ReserveInfo(
        UUID reserveId,
        String reserveName,
        String phoneNumber,
        Long reserveMembers,
        ReserveStatus status,
        String elapsedTime
) {
}