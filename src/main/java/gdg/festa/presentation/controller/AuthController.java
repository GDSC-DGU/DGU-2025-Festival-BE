package gdg.festa.presentation.controller;

import gdg.festa.application.usecase.auth.GetTokenByLoginIdUseCase;
import gdg.festa.application.usecase.auth.RegisterUseCase;
import gdg.festa.core.common.CommonResponseDto;
import gdg.festa.presentation.request.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/login")
@RequiredArgsConstructor
public class AuthController {
    private final GetTokenByLoginIdUseCase getTokenByLoginIdUseCase;
    private final RegisterUseCase registerUseCase;

    @PostMapping("")
    public CommonResponseDto<?> festaLogin(
            @RequestBody LoginRequestDto loginRequestDto
    ) {
        return CommonResponseDto.ok(getTokenByLoginIdUseCase.execute(loginRequestDto));
    }

    @PostMapping("/register")
    public CommonResponseDto<?> register(
            @RequestBody LoginRequestDto loginRequestDto
    ) {
        return CommonResponseDto.ok(registerUseCase.execute(loginRequestDto));
    }
}
