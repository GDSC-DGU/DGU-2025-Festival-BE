package gdg.festa.application.service.reserve;

import gdg.festa.application.dto.reserve.ReadReserveStateDto;
import gdg.festa.application.usecase.reserve.ReadReserveUsecase;
import gdg.festa.domain.entity.Pub;
import gdg.festa.domain.entity.Reserve;
import gdg.festa.domain.repository.PubRepository;
import gdg.festa.domain.repository.ReserveRepository;
import gdg.festa.domain.type.ReserveStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ReadReserveStateService implements ReadReserveUsecase {

    private final ReserveRepository reserveRepository;
//    private final PubRepository pubRepository;


    @Override
    public ReadReserveStateDto execute(String number){

        // 번호 기반 예약 조회
        Reserve reserve = reserveRepository.findByPhoneNumberAndReserveStatus(
                number, ReserveStatus.WAITING
        );

//        Pub pub = pubRepository.findById(reserve.getPub().getPubId()); // 예약 정보 -> 주점 정보 -> 주점 대기 인원 조회
        Integer wait = reserveRepository.findMyOrder(number); // 번호 -> 내 앞의 인원 수 조회
        return ReadReserveStateDto.builder()
                .reserveStatus(reserve.getReserveStatus())
                .waitTeam(wait)
                .build();
    }
}
