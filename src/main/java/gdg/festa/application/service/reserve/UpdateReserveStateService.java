package gdg.festa.application.service.reserve;

import gdg.festa.application.usecase.reserve.UpdateReserveUsecase;
import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.core.util.FcmUtil;
import gdg.festa.domain.entity.Pubs;
import gdg.festa.domain.entity.Reserves;
import gdg.festa.domain.repository.PubsRepository;
import gdg.festa.domain.repository.ReserveRepository;
import gdg.festa.domain.type.ReserveStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UpdateReserveStateService implements UpdateReserveUsecase {

    private final ReserveRepository reserveRepository;
    private final PubsRepository pubsRepository;
    private final FcmUtil fcmUtil;

    @Override
    public Boolean execute(String number) {

        Reserves reserves = reserveRepository.findByPhoneNumber(number);

        /*  예약자가 예약을 취소하는 경우
         *  CANCLE 으로 바뀌는 경우, 대기 인원(-하고,) 해당 2,3순번에게 알람 전송하기
         *
         *  WAITING("WAITING"),CALLED("CALLED"), LATE("LATE"),
         *  인 경우에만 cancel 가능하고, 나머지 상태에서는 각각 요청에 맞는 FCM 메세지 전달하기
         *
         *
         *  */
        log.error("11");
        ReserveStatus status = reserves.getReserveStatus();
        // 취소 가능한 상태 확인
        switch (status) {
            case CANCELED, COMPLETED, ENABLED -> throw new CustomException(switch (status) {
                case CANCELED -> ErrorCode.ALREADY_CANCELD;
                case COMPLETED -> ErrorCode.ALREADY_ENTERED;
                case ENABLED -> ErrorCode.NOT_FOUND_RESERVE;
                default -> throw new CustomException(ErrorCode.NOT_FOUND_RESERVE);
            });
            case WAITING, CALLED, LATE -> {
                // 취소 가능 상태이므로 아무것도 안 하고 통과
            }
            default -> throw new CustomException(ErrorCode.NOT_FOUND_RESERVE);
        }
        // 내 대기 순번이, 3등 이상일때는 그냥 취소처리
        log.error("22");
        // 취소 처리
        reserves.updateStatus(ReserveStatus.CANCELED);

        // 대기 순번 - 하기
        Pubs pubs = reserves.getPubs();
        pubsRepository.decreseWaitPeople(pubs.getPubsId());

        // 취소한 사용자의 대기 순번이 3등 이하인 경우
        Long currentOrder = reserveRepository.findMyOrder(reserves.getPhoneNumber());


        List<Reserves> notifyList = switch (currentOrder.intValue()) {
            case 1 -> reserveRepository.findByPubsAndReserveStatusAndOrderIn(pubs, Arrays.asList(2, 3, 4));
            case 2 -> reserveRepository.findByPubsAndReserveStatusAndOrderIn(pubs, Arrays.asList(3, 4));
            case 3 -> reserveRepository.findByPubsAndReserveStatusAndOrderIn(pubs, Collections.singletonList(4));
            default -> Collections.emptyList();
        };

        notifyList.forEach(reserve -> {
            fcmUtil.sendMessage(
                    pubs.getName() + " 대기 순번 변경 알림",
                    "앞 순서가 취소되어 대기 순번이 앞당겨졌습니다.",
                    reserve.getBrowserToken(),
                    reserve.getReserveId()
            );
        });

        return true;
    }


}

