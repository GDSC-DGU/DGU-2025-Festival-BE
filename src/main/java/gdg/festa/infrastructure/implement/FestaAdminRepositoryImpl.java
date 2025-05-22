package gdg.festa.infrastructure.implement;

import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.domain.entity.FestaAdmin;
import gdg.festa.domain.repository.FestaAdminsRepository;
import gdg.festa.infrastructure.jpa.FestaAdminJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FestaAdminRepositoryImpl implements FestaAdminsRepository {
    private final FestaAdminJpaRepository festaAdminJpaRepository;

    @Override
    public FestaAdmin findByLoginId(String loginId) {
        return festaAdminJpaRepository.findByLoginId(loginId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FESTAADMIN));
    }
}
