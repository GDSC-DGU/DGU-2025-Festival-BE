package gdg.festa.domain.repository;

import gdg.festa.domain.entity.Booths;
import gdg.festa.domain.entity.Pubs;

import java.util.List;

public interface PubsRepository {
    Pubs findById(Long id);
    List<Pubs> findAll();
}
