package gdg.festa.domain.repository;

import gdg.festa.domain.entity.LostImages;
import gdg.festa.domain.entity.Losts;

import java.util.List;
import java.util.Optional;

public interface LostImageRepository {
    void saveAll(List<LostImages> lostImages);
    List<LostImages> findByLostsLostsId(Long lostsId);
    List<LostImages> findByLosts(Losts losts);
    void deleteByLosts(Losts losts);
}
