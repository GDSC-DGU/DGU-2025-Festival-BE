package gdg.festa.domain.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EProvider {
    GOOGLE("GOOGLE"),
    KAKAO("KAKAO"),
    NAVER("NAVER"),
    APPLE("APPLE");

    private final String loginProvider;
}
