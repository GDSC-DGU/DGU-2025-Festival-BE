package gdg.festa.core.security.service;


import gdg.festa.domain.entity.FestaAdmin;
import gdg.festa.domain.entity.PubAdmin;
import gdg.festa.domain.type.ERole;
import gdg.festa.infrastructure.jpa.FestaAdminsJpaRepository;
import gdg.festa.infrastructure.jpa.PubsAdminJpaRepository;
import java.util.UUID;

import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.core.security.info.UserPrincipal;
import gdg.festa.infrastructure.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserJpaRepository userRepositoryImpl;
    private final FestaAdminsJpaRepository festaAdminsJpaRepository;
    private final PubsAdminJpaRepository pubsAdminJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return null;
    }

    public UserDetails loadUserById(UUID id, ERole role) {

        return switch (role) {
            case ADFESTA -> {
                FestaAdmin festaAdmin = festaAdminsJpaRepository.findById(id)
                        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FESTAADMIN));
                yield UserPrincipal.createFesta(festaAdmin);
            }
            case ADPUB -> {
                PubAdmin pubAdmin = pubsAdminJpaRepository.findById(id)
                        .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PUBADMIN));
                yield UserPrincipal.createPub(pubAdmin);
            }
        };
//        User user = userRepositoryImpl.findById(id)
//                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
//
//        return UserPrincipal.createAt(user);
    }

}
