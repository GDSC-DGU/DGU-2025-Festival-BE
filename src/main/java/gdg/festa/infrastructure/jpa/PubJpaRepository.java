package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.Pub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PubJpaRepository extends JpaRepository<Pub, Long> {

    @Query("SELECT p FROM Pub p WHERE p.pubId = :id")
    Optional<Pub> findById(Long id);

    List<Pub> findAll();

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE pubs SET wait_people = wait_people - 1 WHERE pubs_id = :id AND wait_people > 0",
            nativeQuery = true)
    void decreseWaitPeople(Long id);

}
