package gdg.festa.application.service.reserve;

import gdg.festa.application.dto.reserve.ReadReserveStateDto;
import gdg.festa.application.usecase.reserve.ReadReverseUsecase;
import gdg.festa.application.usecase.reserve.UpdateReverseUsecase;
import gdg.festa.domain.entity.Pubs;
import gdg.festa.domain.entity.PubsAdmin;
import gdg.festa.domain.entity.Reserves;
import gdg.festa.domain.repository.PubsRepository;
import gdg.festa.domain.repository.ReserveRepository;
import gdg.festa.domain.type.PubsStatus;
import gdg.festa.domain.type.ReserveStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateReserveStateService implements UpdateReverseUsecase {

    private final ReserveRepository reserveRepository;
    private final PubsRepository pubsRepository;


    @Override
    public Boolean execute(String number, ReserveStatus reserveStatus) {


        Reserves reserves = reserveRepository.findByNumber(number);


        /*
         *  CANCLE 으로 바뀌는 경우, 대기 인원(-하고,) 해당 2,3순번에게 알람 전송하기
         *
         *
         *
         *  */

        reserves.updateStatus(reserveStatus);

        return true;
    }


}

