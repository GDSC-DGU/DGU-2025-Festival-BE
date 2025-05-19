package gdg.festa.application.dto.oauth;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record OAuth2UserInfoResponseDto(
        String oAuthId    // 애플 sub, 카카오 id
) {
    public static OAuth2UserInfoResponseDto of(String oAuthId) {
        return OAuth2UserInfoResponseDto.builder()
                .oAuthId(oAuthId)
                .build();
    }
}
