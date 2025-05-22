package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.LostImage;
import gdg.festa.domain.entity.Lost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LostImageJpaRepository extends JpaRepository<LostImage,Long> {

    List<LostImage> findByLostsLostsId(Long lostsId);
    List<LostImage> findByLosts(Lost lost);

    @Modifying
    @Query("DELETE FROM LostImage li WHERE li.losts = :losts")
    void deleteByLosts(@Param("losts") Lost lost);

    List<LostImage> findByLostsAndDeletedAtIsNull(Lost lost);
}
