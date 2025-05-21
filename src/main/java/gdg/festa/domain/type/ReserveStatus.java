package gdg.festa.domain.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ReserveStatus {

    CANCELED("CANCELED"), // 취소됨
    WAITING("WAITING"),// 대기 중
    COMPLETED("COMPLETED"), // 잘 도착해서 입장함, 술 먹는 중
    CALLED("CALLED"), // 호출 당함 대기1번 호출된 상황 3분 타이머
    LATE("LATE"), //지각생 -> 3분 지각함
    ENABLED("ENABLED"); //휴대폰 인증완료 -> 인증은 완료했지만 아직 예약은 안함

    private final String reserveStatus;
}
