package gdg.festa.application.usecase.user;


import gdg.festa.application.dto.oauth.JwtTokenDto;
import gdg.festa.core.annotation.UseCase;
import gdg.festa.presentation.request.LoginUserRequestDto;

@UseCase
public interface ReadLoginUserUsecase {
    JwtTokenDto execute(LoginUserRequestDto loginUserRequestDto);
}
