package gdg.festa.application.usecase.auth;


import gdg.festa.application.dto.oauth.JwtTokenDto;
import gdg.festa.core.annotation.UseCase;
import gdg.festa.presentation.request.LoginRequestDto;

@UseCase
public interface GetTokenByLoginIdUseCase {
    JwtTokenDto execute(LoginRequestDto loginRequestDto);
}
