package gdg.festa.infrastructure.implement;

import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.domain.entity.PubsAdmin;
import gdg.festa.domain.repository.PubsAdminRepository;
import gdg.festa.infrastructure.jpa.PubsAdminJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PubsAdminRepositoryImpl implements PubsAdminRepository {
    private final PubsAdminJpaRepository pubsAdminJpaRepository;

    @Override
    public PubsAdmin findByLoginId(String loginId) {
        return pubsAdminJpaRepository.findByLoginId(loginId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PUBADMIN));
    }

}
