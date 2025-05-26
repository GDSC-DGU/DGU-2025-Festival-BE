package gdg.festa.application.service.pub;

import gdg.festa.application.usecase.pubs.UpdatePubUsecase;
import gdg.festa.core.util.FcmUtil;
import gdg.festa.domain.entity.Pub;
import gdg.festa.domain.entity.PubAdmin;
import gdg.festa.domain.entity.Reserve;
import gdg.festa.domain.repository.PubAdminRepository;
import gdg.festa.domain.repository.PubRepository;
import gdg.festa.domain.repository.ReserveRepository;
import gdg.festa.domain.type.PubStatus;
import gdg.festa.domain.type.ReserveStatus;
import gdg.festa.infrastructure.sms.SmsUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdatePubService implements UpdatePubUsecase {

    private final PubAdminRepository pubAdminRepository;
    private final PubRepository pubRepository;
    private final ReserveRepository reserveRepository;
    private final FcmUtil fcmUtil;
    private final SmsUtil smsUtil;

    @Override
    public Boolean execute(UUID id, String status) {


        PubAdmin pubAdmin = pubAdminRepository.findById(id);
        Pub pub = pubRepository.findById(pubAdmin.getPub().getPubId());

        PubStatus pubStatus = PubStatus.valueOf(status);
        /* Pub 관리자 전용. 일반 사용자 로직에서 불러오면 안됨.
        *  END 으로 바뀌는 경우, 대기 인원들에게 알람 전송하기
        *  */

        pub.updateState(pubStatus);

        if (pubStatus == PubStatus.END) {
            List<Reserve> reserves = reserveRepository.findAllPubsAndReserveStatus(pub);
            String message = pub.getName() + " 주점 휴식 알림 : 주점 측 사정으로 인해 잠시 운영이 중단됩니다.";
            reserves.forEach(reserve -> {
                reserve.updateStatus(ReserveStatus.CANCELED);
                smsUtil.sendMessage(reserve.getPhoneNumber(), message);
            });
        }


//        fcmUtil.sendMessage(
//                pub.getName() + " 주점 휴식 알림",
//                "주점 측 사정으로 인해 잠시 운영이 중단됩니다.",
//                reserve.getBrowserToken(),
//                reserve.getReserveId()

        /*
         *  END 으로 바뀌는 경우, 대기 인원들 대기 정보 CANCELED로 수정하기

         *
         *  */
//        reserves.stream()
//                .peek(reserve -> reserve.updateStatus(ReserveStatus.CANCELED)) // 상태 변경
//                .forEach(reserve -> fcmUtil.sendMessage(                      // 알림 전송
//                        pub.getName() + " 주점 휴식 알림",
//                        "주점 측 사정으로 인해 잠시 운영이 중단됩니다.",
//                        reserve.getBrowserToken(),
//                        reserve.getReserveId()
//                ));

        return true;
    }

}
