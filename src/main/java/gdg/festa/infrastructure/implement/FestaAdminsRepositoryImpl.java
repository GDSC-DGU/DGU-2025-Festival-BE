package gdg.festa.infrastructure.implement;

import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.domain.entity.FestaAdmins;
import gdg.festa.domain.repository.FestaAdminsRepository;
import gdg.festa.infrastructure.jpa.FestaAdminsJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FestaAdminsRepositoryImpl implements FestaAdminsRepository {
    private final FestaAdminsJpaRepository festaAdminsJpaRepository;

    @Override
    public FestaAdmins findByLoginId(String loginId) {
        return festaAdminsJpaRepository.findByLoginId(loginId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FESTAADMIN));
    }
}
