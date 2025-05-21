package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.Pubs;
import gdg.festa.domain.entity.Reserves;
import gdg.festa.domain.type.ReserveStatus;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

public interface ReserveJpaRepository extends JpaRepository<Reserves, UUID> {
    Optional<Reserves> findByPhoneNumber(String phoneNumber);
    Optional<Reserves> findByPhoneNumberAndReserveStatus(String phoneNumber, ReserveStatus reserveStatus);

    @Query("SELECT r "
            + "FROM Reserves r "
            + "where r.pubs = :pubs AND r.reserveStatus = :reserveStatus "
            + "order by r.createdAt DESC "
            + "limit 2")
    List<Reserves> findByPubsAndReserveStatus(Pubs pubs, ReserveStatus reserveStatus);
}
