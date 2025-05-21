package gdg.festa.application.service.reserve;

import gdg.festa.core.util.FcmUtil;
import gdg.festa.domain.entity.Reserves;
import gdg.festa.domain.repository.ReserveRepository;
import gdg.festa.presentation.request.reserve.CompletedReserveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CalledReserveService {
    private final FcmUtil fcmUtil;
    private final ReserveRepository reserveRepository;

    public Boolean execute(CompletedReserveRequestDto completedReserveRequestDto) {
        Reserves reserves = reserveRepository.findById(completedReserveRequestDto.reserveId());

        fcmUtil.sendMessage(
                reserves.getPubs().getName() + " 주점 입장 가능 알림 ",
                "5분내로 오셔야합니다.",
                reserves.getBrowserToken(),
                reserves.getReserveId()
        );

        return true;
    }
}
