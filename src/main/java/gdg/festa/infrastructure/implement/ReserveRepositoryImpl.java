package gdg.festa.infrastructure.implement;

import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.domain.entity.Pubs;
import gdg.festa.domain.entity.Reserves;
import gdg.festa.domain.repository.ReserveRepository;
import gdg.festa.domain.type.ReserveStatus;
import gdg.festa.infrastructure.jpa.ReserveJpaRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReserveRepositoryImpl implements ReserveRepository {

    private final ReserveJpaRepository reserveJpaRepository;

    @Override
    public Reserves findByPhoneNumber(String number) {
        return reserveJpaRepository.findByPhoneNumber(number)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
    }

    @Override
    public void save(Reserves reserves) {
        reserveJpaRepository.save(reserves);
    }

    @Override
    public Reserves findByPhoneNumberAndReserveStatus(String phoneNumber) {
        return reserveJpaRepository.findByPhoneNumberAndReserveStatus(phoneNumber, ReserveStatus.ENABLED)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_VERIFY));
    }

    @Override
    public Reserves findById(UUID reserveId) {
        return reserveJpaRepository.findById(reserveId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESERVE));
    }

    @Override
    public List<Reserves> findByPubsAndReserveStatus(Pubs pubs) {
        return reserveJpaRepository.findByPubsAndReserveStatus(pubs, ReserveStatus.WAITING);
    }

    @Override
    public List<Reserves> findAllByPubId(Long pubId) {
        return reserveJpaRepository.findAllByPubsId(pubId);
      
    @Override
    public List<Reserves> findAllPubsAndReserveStatus(Pubs pubs) {
        return reserveJpaRepository.findAllPubsAndReserveStatus(pubs, ReserveStatus.WAITING);
    }

    @Override
    public Integer findMyOrder(String phoneNumber) {
        return reserveJpaRepository.findMyOrder(phoneNumber, ReserveStatus.WAITING.name());
    }

    @Override
    public List<Reserves> findByPubsAndReserveStatusAndOrderIn(Long pubsId, List<Integer> orders) {
        return reserveJpaRepository.findByPubsAndReserveStatusAndOrderIn(pubsId, ReserveStatus.WAITING.name(), orders);
    }
}
