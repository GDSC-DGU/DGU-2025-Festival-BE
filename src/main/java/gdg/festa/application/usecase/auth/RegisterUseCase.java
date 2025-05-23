package gdg.festa.application.usecase.auth;

import gdg.festa.core.annotation.UseCase;
import gdg.festa.presentation.request.auth.LoginRequestDto;

@UseCase
public interface RegisterUseCase {
    Boolean execute(LoginRequestDto loginRequestDto);
}
