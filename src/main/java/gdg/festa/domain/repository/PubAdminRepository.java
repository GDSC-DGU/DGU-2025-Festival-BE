package gdg.festa.domain.repository;

import gdg.festa.domain.entity.PubAdmin;
import java.util.UUID;

public interface PubAdminRepository {
    PubAdmin findById(UUID id);

    PubAdmin findByLoginId(String loginId);
}

