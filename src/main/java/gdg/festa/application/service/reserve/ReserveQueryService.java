package gdg.festa.application.service.reserve;

import gdg.festa.application.dto.reserve.ReserveInfo;
import gdg.festa.domain.entity.Reserves;
import gdg.festa.domain.repository.ReserveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReserveQueryService {
    private final ReserveRepository reserveRepository;

    public List<ReserveInfo> readAllReserveInfoOf(Long pubId) {

        List<Reserves> reserves = reserveRepository.findAllByPubId(pubId);

        return reserves.stream()
                .map(reserve -> {
                    // compute elapsed time
                    Duration diff = Duration.between(reserve.getUpdatedAt(), LocalDateTime.now());
                    long minutes = diff.toMinutes();
                    int seconds = diff.toSecondsPart();
                    String elapsedTime = String.format("%d:%02d", minutes, seconds);
                    // build DTO
                    return ReserveInfo.builder()
                            .reserveMembers(reserve.getAttendance())
                            .reserveName(reserve.getName())
                            .reserveId(reserve.getReserveId())
                            .phoneNumber(reserve.getPhoneNumber())
                            .elapsedTime(elapsedTime)
                            .status(reserve.getReserveStatus())
                            .build();
                })
                .toList();
    }
}
