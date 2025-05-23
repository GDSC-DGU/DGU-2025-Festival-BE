package gdg.festa.domain.repository;

import gdg.festa.domain.entity.LostImage;
import gdg.festa.domain.entity.Lost;

import java.util.List;

public interface LostImageRepository {
    void saveAll(List<LostImage> lostImages);
    //List<LostImage> findByLostId(Long lostId);
    List<LostImage> findByLost(Lost lost);
    void deleteByLost(Lost lost);
    List<LostImage> findByLostAndDeletedAtIsNull(Lost lost);
}
