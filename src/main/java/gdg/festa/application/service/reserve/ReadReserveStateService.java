package gdg.festa.application.service.reserve;

import gdg.festa.application.dto.reserve.ReadReserveStateDto;
import gdg.festa.application.usecase.reserve.ReadReserveUsecase;
import gdg.festa.domain.entity.Pubs;
import gdg.festa.domain.entity.Reserves;
import gdg.festa.domain.repository.PubsRepository;
import gdg.festa.domain.repository.ReserveRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ReadReserveStateService implements ReadReserveUsecase {

    private final ReserveRepository reserveRepository;
    private final PubsRepository pubsRepository;


    @Override
    public ReadReserveStateDto execute(String number){

        Reserves reserves = reserveRepository.findByPhoneNumber(number); // 번호 기반 예약 정보 조회
        Pubs pubs = pubsRepository.findById(reserves.getPubs().getPubsId()); // 예약 정보 -> 주점 정보 -> 주점 대기 인원 조회

        return ReadReserveStateDto.builder()
                .reserveStatus(reserves.getReserveStatus())
                .waitTeam(pubs.getWaitPeople())
                .build();
    }
}
