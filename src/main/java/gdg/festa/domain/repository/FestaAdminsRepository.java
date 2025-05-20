package gdg.festa.domain.repository;

import gdg.festa.domain.entity.FestaAdmins;
import gdg.festa.domain.entity.PubsAdmin;

public interface FestaAdminsRepository {
    FestaAdmins findByLoginId(String loginId);
}
