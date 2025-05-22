package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.LostImages;
import gdg.festa.domain.entity.Losts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LostImageJpaRepository extends JpaRepository<LostImages,Long> {

    List<LostImages> findByLostsLostsId(Long lostsId);
    List<LostImages> findByLosts(Losts losts);

    @Modifying
    @Query("DELETE FROM LostImages li WHERE li.losts = :losts")
    void deleteByLosts(@Param("losts") Losts losts);
}
