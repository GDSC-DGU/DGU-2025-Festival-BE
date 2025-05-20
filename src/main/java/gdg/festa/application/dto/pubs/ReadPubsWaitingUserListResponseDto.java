package gdg.festa.application.dto.pubs;

import lombok.Builder;

@Builder
public record ReadPubsWaitingUserListResponseDto(

        Long pubsId, //주점 아이디
        Long waitTeam // 대기 팀 수
) {
}
