package gdg.festa.domain.repository;

import gdg.festa.domain.entity.Lost;

public interface LostsRepository {
    Lost save(Lost lost);
    Lost findById(Long lostsId);
    void deleteById(Long lostsId);
}
