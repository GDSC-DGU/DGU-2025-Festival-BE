package gdg.festa.application.service.auth;

import gdg.festa.application.usecase.auth.RegisterUseCase;
import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.domain.entity.FestaAdmin;
import gdg.festa.domain.entity.PubAdmin;
import gdg.festa.domain.type.ERole;
import gdg.festa.infrastructure.jpa.FestaAdminJpaRepository;
import gdg.festa.infrastructure.jpa.PubAdminJpaRepository;
import gdg.festa.infrastructure.jpa.UserJpaRepository;
import gdg.festa.presentation.request.auth.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterService implements RegisterUseCase {
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final PubAdminJpaRepository pubAdminJpaRepository;
    private final FestaAdminJpaRepository festaAdminJpaRepository;

    @Override
    public UUID execute(LoginRequestDto loginRequestDto) {
        switch (ERole.valueOf(loginRequestDto.role().toUpperCase())) {
            case ADFESTA -> {
                return findAdFesta(loginRequestDto.loginId(), loginRequestDto.password()).getFestaAdminId();
            }
            case ADPUB -> {
                return findAdPub(loginRequestDto.loginId(), loginRequestDto.password()).getPubAdminId();
            }
            default -> throw new CustomException(ErrorCode.INVALID_LOGIN_TYPE);
        }

    }

    private PubAdmin findAdPub(String loginId, String password) {
        PubAdmin pubAdmin = PubAdmin.builder()
                                .loginId(loginId)
                                .password(passwordEncoder.encode(password))
                                .role(ERole.ADPUB)
                                .build();

        return pubAdminJpaRepository.save(pubAdmin);
    }

    private FestaAdmin findAdFesta(String loginId, String password) {
        FestaAdmin festaAdmin = FestaAdmin.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .role(ERole.ADFESTA)
                .build();
        return festaAdminJpaRepository.save(festaAdmin);
    }
}
