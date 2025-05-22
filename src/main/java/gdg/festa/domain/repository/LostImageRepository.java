package gdg.festa.domain.repository;

import gdg.festa.domain.entity.LostImage;
import gdg.festa.domain.entity.Lost;

import java.util.List;

public interface LostImageRepository {
    void saveAll(List<LostImage> lostImages);
    List<LostImage> findByLostsLostsId(Long lostsId);
    List<LostImage> findByLosts(Lost lost);
    void deleteByLosts(Lost lost);
    List<LostImage> findByLostsAndDeletedAtIsNull(Lost lost);
}
