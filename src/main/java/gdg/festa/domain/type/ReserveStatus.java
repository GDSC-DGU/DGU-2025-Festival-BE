package gdg.festa.domain.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ReserveStatus {

    CANCEL("CANCEL"),
    WAITING("WAITING");

    private final String reserveStatus;
}
