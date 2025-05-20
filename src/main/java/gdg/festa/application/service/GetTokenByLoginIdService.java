package gdg.festa.application.service;

import gdg.festa.application.dto.oauth.JwtTokenDto;
import gdg.festa.application.usecase.auth.GetTokenByLoginIdUseCase;
import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.core.util.JwtUtil;
import gdg.festa.core.util.PasswordUtil;
import gdg.festa.domain.entity.FestaAdmins;
import gdg.festa.domain.entity.PubsAdmin;
import gdg.festa.domain.repository.FestaAdminsRepository;
import gdg.festa.domain.repository.PubsAdminRepository;
import gdg.festa.domain.type.ERole;
import gdg.festa.presentation.request.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetTokenByLoginIdService implements GetTokenByLoginIdUseCase {
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final PubsAdminRepository pubsAdminRepository;
    private final FestaAdminsRepository festaAdminsRepository;


    @Override
    public JwtTokenDto execute(LoginRequestDto loginRequestDto) {
        return switch (ERole.valueOf(loginRequestDto.role().toUpperCase())) {
            case ADFESTA -> findAdFesta(loginRequestDto.loginId(), loginRequestDto.password());
            case ADPUB -> findAdPub(loginRequestDto.loginId(), loginRequestDto.password());
        };
    }

    private JwtTokenDto findAdPub(String loginId, String password) {
        PubsAdmin pubsAdmin = pubsAdminRepository.findByLoginId(loginId);
        if(!passwordEncoder.matches(password, pubsAdmin.getPassword()))
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        return jwtUtil.generateTokens(pubsAdmin.getPubsAdminId(), ERole.ADPUB);
    }

    private JwtTokenDto findAdFesta(String loginId, String password) {
        FestaAdmins festaAdmins = festaAdminsRepository.findByLoginId(loginId);
        if(!passwordEncoder.matches(password, festaAdmins.getPassword()))
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        return jwtUtil.generateTokens(festaAdmins.getFestaAdminsId(), ERole.ADFESTA);
    }
}
