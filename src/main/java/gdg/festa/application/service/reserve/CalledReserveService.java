package gdg.festa.application.service.reserve;

import gdg.festa.application.usecase.reserve.CalledReserveUseCase;
import gdg.festa.core.batch.DynamicTaskScheduler;
import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.core.util.FcmUtil;
import gdg.festa.domain.entity.Reserve;
import gdg.festa.domain.repository.ReserveRepository;
import gdg.festa.domain.type.ReserveStatus;
import gdg.festa.presentation.request.reserve.CompletedReserveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CalledReserveService implements CalledReserveUseCase {
    private final FcmUtil fcmUtil;
    private final ReserveRepository reserveRepository;
    private final DynamicTaskScheduler dynamicTaskScheduler;

    public Boolean execute(CompletedReserveRequestDto completedReserveRequestDto) {
        Reserve reserve = reserveRepository.findById(completedReserveRequestDto.reserveId());

        if(!reserve.getReserveStatus().name().equals("WAITING"))
            throw new CustomException(ErrorCode.NOT_YOUR_CALL);

        Long currentPeople = reserve.getPub().getWaitPeople();
        reserve.getPub().updateWaitPeople(currentPeople);

        reserve.updateStatus(ReserveStatus.CALLED);

        fcmUtil.sendMessage(
                reserve.getPub().getName() + " 주점 입장 가능 알림 ",
                "5분내로 오셔야합니다.",
                reserve.getBrowserToken(),
                reserve.getReserveId()
        );

        // 스케쥴러 3분 돌리기
        dynamicTaskScheduler.scheduleSingleUserTask(reserve);

        return true;
    }
}
