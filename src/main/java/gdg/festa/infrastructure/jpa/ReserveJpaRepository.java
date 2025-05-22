package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.Pub;
import gdg.festa.domain.entity.Reserve;
import gdg.festa.domain.type.ReserveStatus;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReserveJpaRepository extends JpaRepository<Reserve, UUID> {
    Optional<Reserve> findByPhoneNumber(String phoneNumber);
    Optional<Reserve> findByPhoneNumberAndReserveStatus(String phoneNumber, ReserveStatus reserveStatus);

    @Query("SELECT r "
            + "FROM Reserve r "
            + "where r.pubs = :pubs AND r.reserveStatus = :reserveStatus "
            + "order by r.createdAt DESC "
            + "limit 2")
    List<Reserve> findByPubsAndReserveStatus(Pub pub, ReserveStatus reserveStatus);

    @Query("SELECT r FROM Reserve r WHERE r.pubs.id = :pubId")
    List<Reserve> findAllByPubsId(Long pubId);

    @Query("SELECT r "
            + "FROM Reserve r "
            + "where r.pubs = :pubs AND r.reserveStatus = :reserveStatus ")
    List<Reserve> findAllPubsAndReserveStatus(Pub pub, ReserveStatus reserveStatus);

    @Query(value = """
    SELECT ranking FROM (
        SELECT
            reserve_id,
            ROW_NUMBER() OVER (ORDER BY created_at) AS ranking
        FROM reserve
        WHERE reserve_state = :reserveStatus
          AND pubs_id = (
              SELECT pubs_id FROM reserve
              WHERE phone_number = :phoneNumber
              AND reserve_state = :reserveStatus
              ORDER BY created_at DESC
              LIMIT 1
          )
    ) ranked
    WHERE reserve_id = (
        SELECT reserve_id FROM reserve
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
        FROM reserve
        WHERE reserve_state = :reserveStatus
          AND pubs_id = :pubsId
    ) AS ordered
    WHERE row_num IN (:orders)
    """, nativeQuery = true)
    List<Reserve> findByPubsAndReserveStatusAndOrderIn(
            Long pubsId,
            String reserveStatus,
            List<Integer> orders
    );
}
