package gdg.festa.infrastructure.implement;

import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.domain.entity.Pub;
import gdg.festa.domain.entity.Reserve;
import gdg.festa.domain.repository.ReserveRepository;
import gdg.festa.domain.type.ReserveStatus;
import gdg.festa.infrastructure.jpa.ReserveJpaRepository;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReserveRepositoryImpl implements ReserveRepository {

    private final ReserveJpaRepository reserveJpaRepository;

    @Override
    public Reserve findByPhoneNumber(String number) {
        return reserveJpaRepository.findByPhoneNumber(number)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
    }

    @Override
    public Reserve save(Reserve reserve) {
        return reserveJpaRepository.save(reserve);
    }

    @Override
    public Reserve findByPhoneNumberAndReserveStatus(String phoneNumber) {
        return reserveJpaRepository.findByPhoneNumberAndReserveStatus(phoneNumber, ReserveStatus.ENABLED)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_VERIFY));
    }

    @Override
    public Reserve findById(UUID reserveId) {
        return reserveJpaRepository.findById(reserveId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESERVE));
    }

    @Override
    public List<Reserve> findByPubsAndReserveStatus(Pub pub) {
        return reserveJpaRepository.findByPubAndReserveStatus(pub, ReserveStatus.WAITING);
    }

    @Override
    public List<Reserve> findAllByPubId(Long pubId) {
        return reserveJpaRepository.findAllByPubId(pubId);
    }

    @Override
    public List<Reserve> findAllPubsAndReserveStatus(Pub pub) {
        return reserveJpaRepository.findAllPubsAndReserveStatus(pub, ReserveStatus.WAITING);
    }

    @Override
    public Integer findMyOrder(String phoneNumber) {
        return reserveJpaRepository.findMyOrder(phoneNumber, ReserveStatus.WAITING.name());
    }

    @Override
    public List<Reserve> findByPubsAndReserveStatusAndOrderIn(Long pubsId, List<Integer> orders) {
        return reserveJpaRepository.findByPubsAndReserveStatusAndOrderIn(pubsId, ReserveStatus.WAITING.name(), orders);
    }

    @Override
    public Boolean existsByPhoneNumberAndReserveStatus(String phoneNumber, ReserveStatus reserveStatus) {
        return reserveJpaRepository.existsByPhoneNumberAndReserveStatus(phoneNumber, reserveStatus);
    }

    @Override
    public void updateUserStatus(@Param("id") UUID id, @Param("status") ReserveStatus status) {
        reserveJpaRepository.updateUserStatus(id, status);
    }

    @Override
    public void deleteAll() {
        reserveJpaRepository.deleteAll();
    }
}

