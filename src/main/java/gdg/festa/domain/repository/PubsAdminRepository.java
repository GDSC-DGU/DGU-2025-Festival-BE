package gdg.festa.domain.repository;

import gdg.festa.domain.entity.Pubs;
import gdg.festa.domain.entity.PubsAdmin;

import java.util.UUID;

public interface PubsAdminRepository {
    PubsAdmin findById(UUID id);
}
