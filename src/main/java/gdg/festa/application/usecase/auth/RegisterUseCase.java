package gdg.festa.application.usecase.auth;

import gdg.festa.core.annotation.UseCase;
import gdg.festa.presentation.request.auth.LoginRequestDto;

import java.util.UUID;

@UseCase
public interface RegisterUseCase {
    UUID execute(LoginRequestDto loginRequestDto);
}
