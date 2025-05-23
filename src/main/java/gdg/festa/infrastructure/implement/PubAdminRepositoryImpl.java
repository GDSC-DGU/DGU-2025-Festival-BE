package gdg.festa.infrastructure.implement;

import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.domain.entity.PubAdmin;
import gdg.festa.domain.repository.PubAdminRepository;
import gdg.festa.infrastructure.jpa.PubAdminJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
@RequiredArgsConstructor
public class PubAdminRepositoryImpl implements PubAdminRepository {

    private final PubAdminJpaRepository pubAdminJpaRepository;

    @Override
    public PubAdmin findById(UUID id) {

        return pubAdminJpaRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_BOOTHS));


    }

    @Override
    public PubAdmin findByLoginId(String loginId) {
        return pubAdminJpaRepository.findByLoginId(loginId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PUBADMIN));
    }
}



