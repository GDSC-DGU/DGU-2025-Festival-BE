package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.Pubs;
import gdg.festa.domain.entity.Reserves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PubsJpaRepository extends JpaRepository<Pubs, Long> {
    Optional<Pubs> findById(Long id);
    List<Pubs> findAll();

    @Modifying
    @Query("UPDATE Pubs p SET p.waitPeople = p.waitPeople - 1 WHERE p.pubsId = :id AND p.waitPeople > 0")
    void decreseWaitPeople(Long id);
}
