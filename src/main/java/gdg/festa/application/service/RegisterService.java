package gdg.festa.application.service;

import gdg.festa.application.dto.oauth.JwtTokenDto;
import gdg.festa.application.usecase.auth.RegisterUseCase;
import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.domain.entity.FestaAdmins;
import gdg.festa.domain.entity.PubsAdmin;
import gdg.festa.domain.type.ERole;
import gdg.festa.infrastructure.jpa.FestaAdminsJpaRepository;
import gdg.festa.infrastructure.jpa.PubsAdminJpaRepository;
import gdg.festa.infrastructure.jpa.UserJpaRepository;
import gdg.festa.presentation.request.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.config.EnableSpringDataWebSupport.QuerydslActivator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterService implements RegisterUseCase {
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final PubsAdminJpaRepository pubsAdminJpaRepository;
    private final FestaAdminsJpaRepository festaAdminsJpaRepository;

    @Override
    public Boolean execute(LoginRequestDto loginRequestDto) {
        switch (ERole.valueOf(loginRequestDto.role().toUpperCase())) {
            case ADFESTA -> {
                findAdFesta(loginRequestDto.loginId(), loginRequestDto.password());
                break;
            }
            case ADPUB -> {
                findAdPub(loginRequestDto.loginId(), loginRequestDto.password());
                break;
            }
            default -> throw new CustomException(ErrorCode.INVALID_LOGIN_TYPE);
        };

        return true;
    }

    private void findAdPub(String loginId, String password) {
        PubsAdmin pubsAdmin = PubsAdmin.builder()
                                .loginId(loginId)
                                .password(passwordEncoder.encode(password))
                                .role(ERole.ADPUB)
                                .build();

        pubsAdminJpaRepository.save(pubsAdmin);
    }

    private void findAdFesta(String loginId, String password) {
        FestaAdmins festaAdmins = FestaAdmins.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .role(ERole.ADFESTA)
                .build();
        festaAdminsJpaRepository.save(festaAdmins);

    }
}
