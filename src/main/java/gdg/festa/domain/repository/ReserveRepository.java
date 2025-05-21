package gdg.festa.domain.repository;

import gdg.festa.domain.entity.Pubs;
import gdg.festa.domain.entity.Reserves;

import gdg.festa.domain.type.ReserveStatus;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;

public interface ReserveRepository {

    Reserves findByNumber(String number);

    void save(Reserves reserves);

    Reserves findByPhoneNumberAndReserveStatus(String phoneNumber);

    Reserves findById(UUID reserveId);


    List<Reserves> findByPubsAndReserveStatus(Pubs pubs);
}
