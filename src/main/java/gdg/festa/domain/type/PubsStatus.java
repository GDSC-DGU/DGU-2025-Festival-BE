package gdg.festa.domain.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PubsStatus {

    AVAILABLE("AVAILABLE"),
    FULL("FULL"),
    PREPARING("PREPARING");

    private final String pubsStatus;
}
