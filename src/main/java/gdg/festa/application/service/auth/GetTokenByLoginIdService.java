package gdg.festa.application.service.auth;

import gdg.festa.application.dto.oauth.JwtTokenDto;
import gdg.festa.application.usecase.auth.GetTokenByLoginIdUseCase;
import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.core.util.JwtUtil;
import gdg.festa.domain.entity.FestaAdmin;
import gdg.festa.domain.entity.PubAdmin;
import gdg.festa.domain.repository.FestaAdminsRepository;
import gdg.festa.domain.repository.PubAdminRepository;
import gdg.festa.domain.type.ERole;
import gdg.festa.presentation.request.auth.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetTokenByLoginIdService implements GetTokenByLoginIdUseCase {
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final PubAdminRepository pubAdminRepository;
    private final FestaAdminsRepository festaAdminsRepository;


    @Override
    public JwtTokenDto execute(LoginRequestDto loginRequestDto) {
        return switch (ERole.valueOf(loginRequestDto.role().toUpperCase())) {
            case ADFESTA -> findAdFesta(loginRequestDto.loginId(), loginRequestDto.password());
            case ADPUB -> findAdPub(loginRequestDto.loginId(), loginRequestDto.password());
        };
    }

    private JwtTokenDto findAdPub(String loginId, String password) {
        PubAdmin pubAdmin = pubAdminRepository.findByLoginId(loginId);
        if(!passwordEncoder.matches(password, pubAdmin.getPassword()))
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        return jwtUtil.generateTokens(pubAdmin.getPubAdminId(), ERole.ADPUB);
    }

    private JwtTokenDto findAdFesta(String loginId, String password) {
        FestaAdmin festaAdmin = festaAdminsRepository.findByLoginId(loginId);
        if(!passwordEncoder.matches(password, festaAdmin.getPassword()))
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        return jwtUtil.generateTokens(festaAdmin.getFestaAdminId(), ERole.ADFESTA);
    }
}
