package gdg.festa.presentation.request;

public record LoginRequestDto(
        String loginId,
        String password,
        String role
) {

}
