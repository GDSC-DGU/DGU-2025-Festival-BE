package gdg.festa.infrastructure.implement;

import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.domain.entity.Pubs;
import gdg.festa.domain.entity.Reserves;
import gdg.festa.domain.repository.ReserveRepository;
import gdg.festa.infrastructure.jpa.ReserveJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReserveRepositoryImpl implements ReserveRepository {

    private final ReserveJpaRepository reserveJpaRepository;

    @Override
    public Reserves findByNumber(String number) {
        return reserveJpaRepository.findByPhoneNumber(number);
    }
}
