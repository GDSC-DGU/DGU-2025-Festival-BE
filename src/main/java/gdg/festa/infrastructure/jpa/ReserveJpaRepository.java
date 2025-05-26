package gdg.festa.infrastructure.jpa;

import gdg.festa.domain.entity.Pub;
import gdg.festa.domain.entity.Reserve;
import gdg.festa.domain.type.ReserveStatus;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReserveJpaRepository extends JpaRepository<Reserve, UUID> {
    Optional<Reserve> findByPhoneNumber(String phoneNumber);

    @Query("SELECT r FROM Reserve r WHERE r.phoneNumber = :phoneNumber " +
            "AND r.reserveStatus = :reserveStatus " +
            "AND r.deletedAt IS NULL ")
    Optional<Reserve> findByPhoneNumberAndReserveStatus(String phoneNumber, ReserveStatus reserveStatus);

    @Query("SELECT r "
            + "FROM Reserve r "
            + "where r.pub = :pub AND r.reserveStatus = :reserveStatus "
            + "order by r.updatedAt ASC "
            + "limit 2")
    List<Reserve> findByPubAndReserveStatus(Pub pub, ReserveStatus reserveStatus);

    @Query("SELECT r FROM Reserve r WHERE r.pub.pubId = :pubId " +
            "order by r.updatedAt ASC")
    List<Reserve> findAllByPubId(Long pubId);

//    @Query("SELECT r "
//            + "FROM Reserve r "
//            + "where r.pub = :pub AND r.reserveStatus = :reserveStatus ")
//    List<Reserve> findAllPubsAndReserveStatus(Pub pub, ReserveStatus reserveStatus);

    @Query("SELECT r FROM Reserve r " +
            "WHERE r.pub = :pub AND r.reserveStatus IN (gdg.festa.domain.type.ReserveStatus.WAITING, gdg.festa.domain.type.ReserveStatus.CALLED)")
    List<Reserve> findAllPubsAndReserveStatus(Pub pub);

    @Query(value = """
    SELECT ranking FROM (
        SELECT
            reserve_id,
            ROW_NUMBER() OVER (ORDER BY updated_at) AS ranking
        FROM reserves
        WHERE reserve_state = :reserveStatus
          AND pub_id = (
              SELECT pub_id FROM reserves
              WHERE reserve_phone_number = :phoneNumber
              AND reserve_state = :reserveStatus
              ORDER BY updated_at ASC
              LIMIT 1
          )
    ) ranked
    WHERE reserve_id = (
        SELECT reserve_id FROM reserves
        WHERE reserve_phone_number = :phoneNumber
        AND reserve_state = :reserveStatus
        ORDER BY updated_at ASC
        LIMIT 1
    )
    """, nativeQuery = true)
    Integer findMyOrder(String phoneNumber, String reserveStatus);

    @Query(value = """
    SELECT * FROM (
        SELECT *,
               ROW_NUMBER() OVER (ORDER BY updated_at) AS row_num
        FROM reserves
        WHERE reserve_state = :reserveStatus
          AND pub_id = :pubId
    ) AS ordered
    WHERE row_num IN (:orders)
    """, nativeQuery = true)
    List<Reserve> findByPubsAndReserveStatusAndOrderIn(
            Long pubId,
            String reserveStatus,
            List<Integer> orders
    );

    Boolean existsByPhoneNumberAndReserveStatus(String phoneNumber, ReserveStatus reserveStatus);

    @Modifying
    @Transactional
    @Query("UPDATE Reserve s SET s.reserveStatus = :status WHERE s.reserveId = :id")
    void updateUserStatus(@Param("id") UUID id, @Param("status") ReserveStatus status);
}
