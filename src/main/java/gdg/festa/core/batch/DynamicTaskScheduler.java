package gdg.festa.core.batch;


import gdg.festa.domain.entity.Reserve;
import gdg.festa.domain.repository.ReserveRepository;
import gdg.festa.domain.type.ReserveStatus;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DynamicTaskScheduler {

    private final ThreadPoolTaskScheduler taskScheduler;
    private final ReserveRepository reserveRepository;

    private final Map<UUID, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

    @Transactional
    public void scheduleSingleUserTask(Reserve reserve) {
        LocalTime adjustedTime = LocalTime.from(LocalDateTime.now().plusMinutes(5));
        long delay = calculateDelay(adjustedTime);

        scheduleTask(reserve, delay);
    }

    private void scheduleTask(Reserve reserve, long delay) {
        ScheduledFuture<?> future = taskScheduler.schedule(
                () -> {
                    Reserve checkReserve = reserveRepository.findById(reserve.getReserveId());
                    if (checkReserve.getReserveStatus().name().equals("CALLED")) {
                        reserveRepository.updateUserStatus(checkReserve.getReserveId(), ReserveStatus.LATE);
                    }
                },
                new Date(System.currentTimeMillis() + delay)
        );

        ScheduledFuture<?> existingTask = scheduledTasks.put(reserve.getReserveId(), future);
        if (existingTask != null) {
            existingTask.cancel(false);
            log.info("기존 작업 취소: 사용자 {}", reserve.getName());
        }

        log.info("스케줄링 예약: 사용자 {}, 지연: {} ms", reserve.getName(), delay);
    }

    private long calculateDelay(LocalTime attendanceTime) {
        long delay = Duration.between(LocalTime.now(), attendanceTime).toMillis();
        return delay >= 0 ? delay : TimeUnit.DAYS.toMillis(1) + delay; // 다음 날로 예약
    }

}
