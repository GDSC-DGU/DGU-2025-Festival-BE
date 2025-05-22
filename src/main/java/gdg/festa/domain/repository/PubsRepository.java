package gdg.festa.domain.repository;

import gdg.festa.domain.entity.Pub;

import java.util.List;

public interface PubsRepository {
    Pub findById(Long id);
    List<Pub> findAll();

    void decreseWaitPeople(Long id);
}
