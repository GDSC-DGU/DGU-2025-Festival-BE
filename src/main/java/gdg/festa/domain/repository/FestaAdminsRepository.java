package gdg.festa.domain.repository;

import gdg.festa.domain.entity.FestaAdmin;

public interface FestaAdminsRepository {
    FestaAdmin findByLoginId(String loginId);
}
