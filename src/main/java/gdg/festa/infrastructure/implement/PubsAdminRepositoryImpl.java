package gdg.festa.infrastructure.implement;

import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.domain.entity.Pubs;
import gdg.festa.domain.entity.PubsAdmin;
import gdg.festa.domain.repository.PubsAdminRepository;
import gdg.festa.domain.repository.PubsRepository;
import gdg.festa.infrastructure.jpa.PubsAdminJpaRepository;
import gdg.festa.infrastructure.jpa.PubsJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
@RequiredArgsConstructor
public class PubsAdminRepositoryImpl implements PubsAdminRepository {

    private final PubsAdminJpaRepository pubsAdminJpaRepository;

    @Override
    public PubsAdmin findById(UUID id) {

        return pubsAdminJpaRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_BOOTHS));
    }
}
