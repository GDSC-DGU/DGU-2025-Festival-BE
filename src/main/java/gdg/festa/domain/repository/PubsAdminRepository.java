package gdg.festa.domain.repository;

import gdg.festa.domain.entity.PubAdmin;
import java.util.UUID;

public interface PubsAdminRepository {
    PubAdmin findById(UUID id);

    PubAdmin findByLoginId(String loginId);
}

