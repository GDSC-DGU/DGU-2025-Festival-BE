package gdg.festa.domain.repository;

import gdg.festa.domain.entity.Pub;
import gdg.festa.domain.entity.Reserve;

import gdg.festa.domain.type.ReserveStatus;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.query.Param;

public interface ReserveRepository {

    Reserve findByPhoneNumber(String number);

    void save(Reserve reserve);

    Reserve findByPhoneNumberAndReserveStatus(String phoneNumber);

    Reserve findById(UUID reserveId);


    List<Reserve> findByPubsAndReserveStatus(Pub pub);
  
    List<Reserve> findAllByPubId(Long pubId);
  
    List<Reserve> findAllPubsAndReserveStatus(Pub pub);

    Integer findMyOrder(String phoneNumber);

    List<Reserve> findByPubsAndReserveStatusAndOrderIn(Long pubsId, List<Integer> orders);

    Boolean existsByPhoneNumberAndReserveStatus(String phoneNumber, ReserveStatus reserveStatus);

    void updateUserStatus(@Param("id") UUID id, @Param("status") ReserveStatus status);
}
