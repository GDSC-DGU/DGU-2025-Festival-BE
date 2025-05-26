package gdg.festa.application.service.reserve;

import gdg.festa.application.usecase.reserve.CreateReserveUseCase;
import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.domain.entity.Pub;
import gdg.festa.domain.entity.Reserve;
import gdg.festa.domain.repository.PubRepository;
import gdg.festa.domain.repository.ReserveRepository;
import gdg.festa.domain.type.ReserveStatus;
import gdg.festa.presentation.request.reserve.CreateReserveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateReserveService implements CreateReserveUseCase {
    private final ReserveRepository reserveRepository;
    private final PubRepository pubRepository;

    @Override
    public Boolean execute(Long boothId, CreateReserveRequestDto createReserveRequestDto) {
        Pub pub = pubRepository.findById(boothId);
        if (pub.getPubStatus().name().equals("AVAILABLE"))
            throw new CustomException(ErrorCode.ACCESS_AVAILABLE); // 예약없이 입장이 가능합니다.

        if (pub.getPubStatus().name().equals("END") || pub.getPubStatus().name().equals("PREPARING"))
            throw new CustomException(ErrorCode.ACCESS_STOP); // 부스가 운영을 중단하였습니다.

        if (reserveRepository.existsByPhoneNumberAndReserveStatus(createReserveRequestDto.phoneNumber(), ReserveStatus.WAITING))
            throw new CustomException(ErrorCode.CONFLICT_RESERVE);

        if (reserveRepository.existsByPhoneNumberAndReserveStatus(createReserveRequestDto.phoneNumber(), ReserveStatus.CALLED))
            throw new CustomException(ErrorCode.CONFLICT_RESERVE);

        Reserve reserve = reserveRepository.findByPhoneNumberAndReserveStatusIsEnabled(
                createReserveRequestDto.phoneNumber()
        );

        reserve.updateReserve(
                createReserveRequestDto.attendance(),
                createReserveRequestDto.name(),
                pub
        );

        Long currentPeople = reserve.getPub().getWaitPeople();
        pub.updateAddWaitPeople(currentPeople);

        return true;
    }
}
