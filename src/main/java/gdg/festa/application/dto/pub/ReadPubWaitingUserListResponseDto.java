package gdg.festa.application.dto.pub;

import gdg.festa.application.dto.reserve.ReserveInfo;
import lombok.Builder;

import java.util.List;

@Builder
public record ReadPubWaitingUserListResponseDto(
        String pubStatus,
        Long waitingTotalCount, // 전체 현재 대기 팀 수
        Long lateTotalCount, // 전체 시간 경과 대기 팀 수
        List<ReserveInfo> reserveList // 대기 팀 리스트
) {

}