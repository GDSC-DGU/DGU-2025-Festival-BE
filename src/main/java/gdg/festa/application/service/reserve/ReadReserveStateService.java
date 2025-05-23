package gdg.festa.application.service.reserve;

import gdg.festa.application.dto.reserve.ReadReserveStateDto;
import gdg.festa.application.usecase.reserve.ReadReserveUsecase;
import gdg.festa.domain.entity.Pub;
import gdg.festa.domain.entity.Reserve;
import gdg.festa.domain.repository.PubRepository;
import gdg.festa.domain.repository.ReserveRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ReadReserveStateService implements ReadReserveUsecase {

    private final ReserveRepository reserveRepository;
    private final PubRepository pubRepository;


    @Override
    public ReadReserveStateDto execute(String number){

        Reserve reserve = reserveRepository.findByPhoneNumber(number); // 번호 기반 예약 정보 조회
        Pub pub = pubRepository.findById(reserve.getPub().getPubId()); // 예약 정보 -> 주점 정보 -> 주점 대기 인원 조회

        return ReadReserveStateDto.builder()
                .reserveStatus(reserve.getReserveStatus())
                .waitTeam(pub.getWaitPeople())
                .build();
    }
}
