package gdg.festa.application.dto.reserve;

import gdg.festa.domain.type.ReserveStatus;
import lombok.Builder;

@Builder
public record ReadReserveStateDto(
        Integer waitTeam, // 앞의 대기 팀
        ReserveStatus reserveStatus // 사용자의 예약 상태 (ex. 예약중, 예약 취소)
) {
}
