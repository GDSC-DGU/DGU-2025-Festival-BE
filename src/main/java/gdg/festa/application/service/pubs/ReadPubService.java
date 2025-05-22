package gdg.festa.application.service.pubs;

import gdg.festa.application.dto.pubs.ReadPubWaitingUserListResponseDto;
import gdg.festa.application.dto.reserve.ReserveInfo;
import gdg.festa.application.service.reserve.ReserveQueryService;
import gdg.festa.application.usecase.pubs.ReadAdminPubsUsecase;
import gdg.festa.domain.entity.PubAdmin;
import gdg.festa.domain.repository.PubsAdminRepository;
import gdg.festa.domain.type.ReserveStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReadPubService implements ReadAdminPubsUsecase {

    private final PubsAdminRepository pubsAdminRepository;
    private final ReserveQueryService reserveQueryService;

    @Override
    public ReadPubWaitingUserListResponseDto execute(UUID pubAdminId) {

        PubAdmin pubAdmin = pubsAdminRepository.findById(pubAdminId);

        Long pubId = pubAdmin.getPub().getPubId();

        List<ReserveInfo> reserveList = reserveQueryService.readAllReserveInfoOf(pubId);

        return ReadPubWaitingUserListResponseDto.builder()
                .waitingTotalCount(reserveList.stream().filter(
                        reserveInfo -> reserveInfo.status().equals(ReserveStatus.WAITING)
                ).count(
                ))
                .lateTotalCount(reserveList.stream().filter(
                        reserveInfo -> reserveInfo.status().equals(ReserveStatus.LATE)
                ).count(
                ))
                .reserveList(
                        reserveList
                )
                .build();
    }
}
