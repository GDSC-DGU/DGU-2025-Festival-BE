package gdg.festa.domain.repository;

import gdg.festa.domain.entity.PubsAdmin;

public interface PubsAdminRepository {
    PubsAdmin findByLoginId(String loginId);
}
