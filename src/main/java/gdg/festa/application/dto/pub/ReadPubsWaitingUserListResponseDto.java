package gdg.festa.application.dto.pub;

import gdg.festa.domain.type.PubStatus;
import lombok.Builder;

@Builder
public record ReadPubsWaitingUserListResponseDto(

        Long pubsId, //주점 아이디
        Long waitTeam, // 대기 팀 수
        PubStatus status // 주점 상태
) {
}
