package gdg.festa.core.constant;

import java.util.List;

public final class Constants {
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String USER_ID_CLAIM_NAME = "userId";
    public static final String USER_ROLE_CLAIM_NAME = "userRole";
    public static final String REDIS_LOST_KEY_PREFIX = "losts:";
    public static final String REDIS_NOTICE_KEY_PREFIX = "notices:";
    public static final String CONTENT_TYPE = "Content-Type";

    // 인증이 필요 없는 URL
    public static final List<String> NO_NEED_AUTH_URLS = List.of(
            "/api/v1/test/hello",
            "/api/v1/test/signin/{name}",
            "/api/v1/auth/login/google",
            "/api/v1/auth/login/apple",
            "/oauth/login/google",
            "/oauth/login/google/callback",
            "/api/v1/auth/admin/login",
            "/admin/login/register",
            "/admin/login/**",
            "/sms/**",
            "/reserve/**",
            "/pubs", // 모든 주점 조회
            "/ws",
            "/reserve", // 번호 기반 예약 정보 조회
            "/ws-stomp",
            "/ws-stomp/**",
            "/pub/**",
            "/sub/**"
    );
}
