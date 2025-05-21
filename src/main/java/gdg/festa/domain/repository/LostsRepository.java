package gdg.festa.domain.repository;

import gdg.festa.domain.entity.Losts;

import java.util.Optional;

public interface LostsRepository {
    Losts save(Losts losts);
    Losts findById(Long lostsId);
    void deleteById(Long lostsId);
}
