package gdg.festa.application.service.pubs;

import gdg.festa.application.usecase.pubs.UpdatePubsUsecase;
import gdg.festa.core.util.FcmUtil;
import gdg.festa.domain.entity.Pubs;
import gdg.festa.domain.entity.PubsAdmin;
import gdg.festa.domain.entity.Reserves;
import gdg.festa.domain.repository.PubsAdminRepository;
import gdg.festa.domain.repository.PubsRepository;
import gdg.festa.domain.repository.ReserveRepository;
import gdg.festa.domain.type.PubsStatus;
import gdg.festa.domain.type.ReserveStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdatePubService implements UpdatePubsUsecase {

    private final PubsAdminRepository pubsAdminRepository;
    private final PubsRepository pubsRepository;
    private final ReserveRepository reserveRepository;
    private final FcmUtil fcmUtil;

    @Override
    public Boolean execute(UUID id, String status) {


        PubsAdmin pubsAdmin = pubsAdminRepository.findById(id);
        Pubs pubs = pubsRepository.findById(pubsAdmin.getPubs().getPubsId());

        PubsStatus pubsStatus = PubsStatus.valueOf(status);
        /* Pub 관리자 전용. 일반 사용자 로직에서 불러오면 안됨.
        *  END 으로 바뀌는 경우, 대기 인원들에게 알람 전송하기
        *  */

        pubs.updateState(pubsStatus);

        if (pubsStatus == PubsStatus.END) {
            List<Reserves> reserves = reserveRepository.findAllPubsAndReserveStatus(pubs);
            reserves.stream()
                    .forEach(reserve -> fcmUtil.sendMessage(
                            pubs.getName() + " 주점 휴식 알림",
                            "주점 측 사정으로 인해 잠시 운영이 중단됩니다.",
                            reserve.getBrowserToken(),
                            reserve.getReserveId()
                    ));
        }

        /*
         *  END 으로 바뀌는 경우, 대기 인원들 대기 정보 CANCELED로 수정하기

         *
         *  */
//        reserves.stream()
//                .peek(reserve -> reserve.updateStatus(ReserveStatus.CANCELED)) // 상태 변경
//                .forEach(reserve -> fcmUtil.sendMessage(                      // 알림 전송
//                        pubs.getName() + " 주점 휴식 알림",
//                        "주점 측 사정으로 인해 잠시 운영이 중단됩니다.",
//                        reserve.getBrowserToken(),
//                        reserve.getReserveId()
//                ));

        return true;
    }

}
