package gdg.festa.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TagStatus {
    CLOTH("의류"),
    DEVICE("전자기기"),
    WALLET("지갑"),
    CARD("카드"),
    BEAUTY("미용용품"),
    ETC("기타");

    private final String description;
}
