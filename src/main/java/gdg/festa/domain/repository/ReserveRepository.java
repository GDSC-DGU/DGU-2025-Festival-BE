package gdg.festa.domain.repository;

import gdg.festa.domain.entity.Reserves;

import gdg.festa.domain.type.ReserveStatus;
import java.util.List;

public interface ReserveRepository {

    Reserves findByNumber(String number);

    void save(Reserves reserves);

    Reserves findByPhoneNumberAndReserveStatus(String phoneNumber);

}
