package gdg.festa.application.service.reserve;

import gdg.festa.application.usecase.reserve.UpdateReserveUsecase;
import gdg.festa.domain.entity.Reserves;
import gdg.festa.domain.repository.PubsRepository;
import gdg.festa.domain.repository.ReserveRepository;
import gdg.festa.domain.type.ReserveStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateReserveStateService implements UpdateReserveUsecase {

    private final ReserveRepository reserveRepository;

    @Override
    public Boolean execute(String number) {


        Reserves reserves = reserveRepository.findByPhoneNumber(number);


        /*
         *  CANCLE 으로 바뀌는 경우, 대기 인원(-하고,) 해당 2,3순번에게 알람 전송하기
         *
         *  WAITING("WAITING"),// 대기 중
         *  CALLED("CALLED"), // 호출 당함 대기1번 호출된 상황 3분 타이머
         *  LATE("LATE"), //지각생 -> 3분 지각함
         *  인 경우에만 cancel 가능하고, 나머지 상태에서는 각각 요청에 맞는 에러 메세지 전달하기
         *
         *
         *  */

        reserves.updateStatus(ReserveStatus.CANCELED);

        return true;
    }


}

