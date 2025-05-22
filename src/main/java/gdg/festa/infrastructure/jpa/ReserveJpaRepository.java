package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.Pubs;
import gdg.festa.domain.entity.Reserves;
import gdg.festa.domain.type.PubsStatus;
import gdg.festa.domain.type.ReserveStatus;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

public interface ReserveJpaRepository extends JpaRepository<Reserves, UUID> {
    Optional<Reserves> findByPhoneNumber(String phoneNumber);
    Optional<Reserves> findByPhoneNumberAndReserveStatus(String phoneNumber, ReserveStatus reserveStatus);

    @Query("SELECT r "
            + "FROM Reserves r "
            + "where r.pubs = :pubs AND r.reserveStatus = :reserveStatus "
            + "order by r.createdAt DESC "
            + "limit 2")
    List<Reserves> findByPubsAndReserveStatus(Pubs pubs, ReserveStatus reserveStatus);

    @Query("SELECT r FROM Reserves r WHERE r.pubs.id = :pubId")
    List<Reserves> findAllByPubsId(Long pubId);

    @Query("SELECT r "
            + "FROM Reserves r "
            + "where r.pubs = :pubs AND r.reserveStatus = :reserveStatus ")
    List<Reserves> findAllPubsAndReserveStatus(Pubs pubs, ReserveStatus reserveStatus);

    @Query(value = """
    SELECT ranking FROM (
        SELECT
            reserve_id,
            ROW_NUMBER() OVER (ORDER BY created_at) AS ranking
        FROM reserves
        WHERE reserve_state = :reserveStatus
          AND pubs_id = (
              SELECT pubs_id FROM reserves
              WHERE phone_number = :phoneNumber
              AND reserve_state = :reserveStatus
              ORDER BY created_at DESC
              LIMIT 1
          )
    ) ranked
    WHERE reserve_id = (
        SELECT reserve_id FROM reserves
        WHERE phone_number = :phoneNumber
        AND reserve_state = :reserveStatus
        ORDER BY created_at DESC
        LIMIT 1
    )
    """, nativeQuery = true)
    Integer findMyOrder(String phoneNumber, String reserveStatus);

    @Query(value = """
    SELECT * FROM (
        SELECT *,
               ROW_NUMBER() OVER (ORDER BY created_at) AS row_num
        FROM reserves
        WHERE reserve_state = :reserveStatus
          AND pubs_id = :pubsId
    ) AS ordered
    WHERE row_num IN (:orders)
    """, nativeQuery = true)
    List<Reserves> findByPubsAndReserveStatusAndOrderIn(
            Long pubsId,
            String reserveStatus,
            List<Integer> orders
    );
}
