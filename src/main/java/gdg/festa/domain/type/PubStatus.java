package gdg.festa.domain.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PubStatus {

    AVAILABLE("AVAILABLE"),
    FULL("FULL"),
    END("END"),
    PREPARING("PREPARING");

    private final String pubsStatus;
}
