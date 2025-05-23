package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.LostImage;
import gdg.festa.domain.entity.Lost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LostImageJpaRepository extends JpaRepository<LostImage,Long> {

    List<LostImage> findByLostId(Long lostId);
    List<LostImage> findByLost(Lost lost);

    @Modifying
    @Query("DELETE FROM LostImage li WHERE li.lost = :lost")
    void deleteByLost(@Param("lost") Lost lost);

    List<LostImage> findByLostAndDeletedAtIsNull(Lost lost);
}
