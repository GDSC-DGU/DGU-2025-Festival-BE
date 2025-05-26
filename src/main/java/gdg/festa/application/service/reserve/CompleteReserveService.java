package gdg.festa.application.service.reserve;

import gdg.festa.application.usecase.reserve.CompleteReserveUseCase;
import gdg.festa.core.batch.DynamicTaskScheduler;
import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.core.util.FcmUtil;
import gdg.festa.domain.entity.PubAdmin;
import gdg.festa.domain.entity.Reserve;
import gdg.festa.domain.repository.PubAdminRepository;
import gdg.festa.domain.repository.ReserveRepository;
import gdg.festa.domain.type.ReserveStatus;
import gdg.festa.presentation.request.reserve.CompletedReserveRequestDto;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CompleteReserveService implements CompleteReserveUseCase {
    private final ReserveRepository reserveRepository;
    private final PubAdminRepository pubAdminRepository;
    private final FcmUtil fcmUtil;
    private final DynamicTaskScheduler dynamicTaskScheduler;

    @Override
    public Boolean execute(UUID adminId, CompletedReserveRequestDto completedReserveRequestDto) {
        Reserve reserve = reserveRepository.findById(completedReserveRequestDto.reserveId());

        if(!(reserve.getReserveStatus().name().equals("CALLED") || reserve.getReserveStatus().name().equals("LATE")))
            throw new CustomException(ErrorCode.NOT_YOUR_TURN); // 입장 대상자가 아닙니다.

        reserve.updateStatus(ReserveStatus.COMPLETED);

        PubAdmin pubAdmin = pubAdminRepository.findById(adminId);

        List<Reserve> nextReserve = reserveRepository.findByPubsAndReserveStatus(pubAdmin.getPub());
        //fcm 근처에서 대기하십쇼
        nextReserve.forEach(
                reserves1 -> fcmUtil.sendMessage(
                        reserves1.getPub().getName() + " 주점 대기 번호 임박 알림 ",
                        "대기 번호가 가까워 졌습니다. 부스 근처에서 대기해주세요.",
                        reserves1.getBrowserToken(),
                        reserves1.getReserveId()
                )
        );

        return true;

    }
}
