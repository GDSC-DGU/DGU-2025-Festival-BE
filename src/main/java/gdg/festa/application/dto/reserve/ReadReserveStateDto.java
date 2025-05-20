package gdg.festa.application.dto.reserve;

import lombok.Builder;

@Builder
public record ReadReserveStateDto(
        Long waitTeam, // 앞의 대기 팀
        String state // 사용자의 예약 상태 (ex. 예약중, 예약 취소)
) {
}
