package gdg.festa.core.constant;

import java.util.List;

public final class Constants {
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String USER_ID_CLAIM_NAME = "userId";
    public static final String USER_ROLE_CLAIM_NAME = "userRole";
    public static final String CONTENT_TYPE = "Content-Type";

    public static final String KAKAO_USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";
    public static final String APPLICATION_FORM_URLENCODED_WITH_CHARSET = "application/x-www-form-urlencoded;charset=utf-8";
    public static final String APPLE_PUBLIC_KEYS_URL = "https://appleid.apple.com/auth/keys";
    public static final String MATCHING_QUEUE_NAME = "matching.queue";
    public static final String MATCHING_EXCHANGE_NAME = "matching.exchange";
    public static final String MATCHING_ROUTING_KEY = "matching.key";

    // 인증이 필요 없는 URL
    public static final List<String> NO_NEED_AUTH_URLS = List.of(
            "/api/v1/test/hello",
            "/api/v1/test/signin/{name}",
            "/api/v1/auth/login/google",
            "/api/v1/auth/login/apple",
            "/oauth/login/google",
            "/oauth/login/google/callback",
            "/api/v1/auth/admin/login",

            //성우
            "/admin/pub/{pubAdminId}",
            "/pubs",

            "/ws",
            "/ws-stomp",
            "/ws-stomp/**",
            "/pub/**",
            "/sub/**"
    );
}
