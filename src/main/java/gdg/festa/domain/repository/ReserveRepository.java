package gdg.festa.domain.repository;

import gdg.festa.domain.entity.Reserves;

import java.util.List;

public interface ReserveRepository {

    Reserves findByNumber(String number);

}
