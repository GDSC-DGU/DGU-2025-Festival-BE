package gdg.festa.application.service.pub;

import gdg.festa.application.usecase.pubs.DeleteAdminReserveUseCase;
import gdg.festa.core.util.FcmUtil;
import gdg.festa.domain.entity.Reserve;
import gdg.festa.domain.repository.ReserveRepository;
import gdg.festa.domain.type.ReserveStatus;
import gdg.festa.infrastructure.sms.SmsUtil;
import gdg.festa.presentation.request.reserve.CompletedReserveRequestDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteAdminReserveService implements DeleteAdminReserveUseCase {
    private final ReserveRepository reserveRepository;
    private final FcmUtil fcmUtil;
    private final SmsUtil smsUtil;

    @Override
    public Boolean execute(CompletedReserveRequestDto completedReserveRequestDto, UUID adminId) {
        Reserve reserve = reserveRepository.findById(completedReserveRequestDto.reserveId());

        Long currentPeople = reserve.getPub().getWaitPeople();

        if(reserve.getReserveStatus().equals(ReserveStatus.WAITING))
            reserve.getPub().updateWaitPeople(currentPeople);

        reserve.updateStatus(ReserveStatus.CANCELED);

        String message = reserve.getPub().getName() + " 대기자 취소알림: 주점측에서 대기를 취소하였습니다.";

        smsUtil.sendMessage(reserve.getPhoneNumber(), message);
        return true;

//        fcmUtil.sendMessage(
//                reserve.getPub().getName() + " 대기자 취소알림  ",
//                "주점측에서 대기를 취소하였습니다. ",
//                reserve.getBrowserToken(),
//                reserve.getReserveId()
//        );
//
//        return true;

    }
}
