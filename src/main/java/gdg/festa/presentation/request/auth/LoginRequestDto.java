package gdg.festa.presentation.request.auth;

public record LoginRequestDto(
        String loginId,
        String password,
        String role
) {

}
