package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.Pubs;
import gdg.festa.domain.entity.Reserves;
import gdg.festa.domain.type.PubsStatus;
import gdg.festa.domain.type.ReserveStatus;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReserveJpaRepository extends JpaRepository<Reserves, UUID> {
    Optional<Reserves> findByPhoneNumber(String phoneNumber);
    Optional<Reserves> findByPhoneNumberAndReserveStatus(String phoneNumber, ReserveStatus reserveStatus);

    @Query("SELECT r "
            + "FROM Reserves r "
            + "where r.pubs = :pubs AND r.reserveStatus = :reserveStatus "
            + "order by r.createdAt DESC "
            + "limit 2")
    List<Reserves> findByPubsAndReserveStatus(Pubs pubs, ReserveStatus reserveStatus);
    @Query("SELECT r "
            + "FROM Reserves r "
            + "where r.pubs = :pubs AND r.reserveStatus = :reserveStatus ")
    List<Reserves> findAllPubsAndReserveStatus(Pubs pubs, ReserveStatus reserveStatus);

    @Query(value = """
    SELECT ranking FROM (
        SELECT
            phone_number,
            ROW_NUMBER() OVER (ORDER BY created_at) AS ranking
        FROM reserves
        WHERE reserve_state = :reserveStatus
    ) ranked
    WHERE phone_number = :phoneNumber
    """, nativeQuery = true)
    Long findMyOrder(String phoneNumber, ReserveStatus reserveStatus);

    @Query(value = """
    SELECT * FROM (
        SELECT *,
               ROW_NUMBER() OVER (ORDER BY created_at) AS row_num
        FROM reserves
        WHERE reserve_state = :reserveStatus
    ) AS ordered
    WHERE row_num IN (:orders)
    """, nativeQuery = true)
    List<Reserves> findByPubsAndReserveStatusAndOrderIn(
            Pubs pubs,
            ReserveStatus reserveStatus,
            List<Integer> orders
    );
}
