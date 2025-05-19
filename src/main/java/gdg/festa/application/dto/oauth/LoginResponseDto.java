package gdg.festa.application.dto.oauth;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record LoginResponseDto(
        @NotNull(message = "토큰 발급에 실패하였습니다.")
        JwtTokenDto jwtTokenDto,
        @NotNull(message = "다시 로그인해주세요.")
        Boolean userFlag
) {
    @Builder
    public LoginResponseDto(JwtTokenDto jwtTokenDto, Boolean userFlag) {
        this.jwtTokenDto = jwtTokenDto;
        this.userFlag = userFlag;
    }

    public static LoginResponseDto of(JwtTokenDto jwtTokenDto, Boolean userFlag) {
        return LoginResponseDto.builder()
                .jwtTokenDto(jwtTokenDto)
                .userFlag(userFlag)
                .build();
    }
}
