package gdg.festa.reserve;

import gdg.festa.IntegrationTestContainer;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Rollback
@Transactional
public class ReserveIntegrationTest extends IntegrationTestContainer {

    @Test
    @DisplayName("전화번호 인증을 마친 사용자는 만석인 야간 부스에 예약을 할 수 있다.")
    public void createReserveTest() {
        // 예약 생성 테스트 로직 작성
        // 예: createReserveUseCase.execute(boothId, requestDto);
        // Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("어느 야간 부스에 예약을 이미 한 사용자는 해당 부스와 다른 부스에 다시 예약을 할 수 없다.")
    public void createReserveFailTest() {
        // 예약 실패 테스트 로직 작성
        // 예: createReserveUseCase.execute(boothId, requestDto);
        // Assertions.assertThrows(AlreadyReservedException.class, () -> {
        //     createReserveUseCase.execute(boothId, requestDto);
        // });
    }

    @Test
    @DisplayName("예약을 취소한 사용자는 해당 부스와 다른 부스에 다시 예약을 할 수 있다.")
    public void cancelReserveTest() {
        // 예약 취소 후 재예약 테스트 로직 작성
        // 예: updateReserveUsecase.execute(number);
        // Assertions.assertTrue(createReserveUseCase.execute(boothId, requestDto));
    }

    @Test
    @DisplayName("예약을 원하는 부스가 바로 입장 가능한 상태라면 예약을 할 수 없다.")
    public void createReserveFailWhenBoothAvailableTest() {
        // 바로 입장 가능한 부스에 예약 시도 테스트 로직 작성
        // 예: createReserveUseCase.execute(boothId, requestDto);
        // Assertions.assertThrows(BoothAvailableException.class, () -> {
        //     createReserveUseCase.execute(boothId, requestDto);
        // });
    }

    @Test
    @DisplayName("예약을 원하는 부스가 만석인 상태라면 예약을 할 수 있다.")
    public void createReserveSuccessWhenBoothFullTest() {
        // 만석인 부스에 예약 시도 테스트 로직 작성
        // 예: createReserveUseCase.execute(boothId, requestDto);
        // Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("예약을 원하는 부스가 준비 중이거나 영업 종료된 상태라면 예약을 할 수 없다.")
    public void createReserveFailWhenBoothPreparingOrClosedTest() {
        // 준비 중이거나 영업 종료된 부스에 예약 시도 테스트 로직 작성
        // 예: createReserveUseCase.execute(boothId, requestDto);
        // Assertions.assertThrows(BoothClosedException.class, () -> {
        //     createReserveUseCase.execute(boothId, requestDto);
        // });
    }

    @Test
    @DisplayName("예약 시 해당 부스의 대기 팀 수가 증가한다.")
    public void reserveIncreasesWaitingTeamCountTest() {
        // 예약 후 대기 팀 수 증가 테스트 로직 작성
        // 예: createReserveUseCase.execute(boothId, requestDto);
        // Assertions.assertEquals(expectedWaitingCount, booth.getWaitingTeamCount());
    }

    @Test
    @DisplayName("예약 취소 시 해당 부스의 대기 팀 수가 감소하고 예약 상태가 CANCELED로 변경된다.")
    public void cancelReserveDecreasesWaitingTeamCountTest() {
        // 예약 취소 후 대기 팀 수 감소 테스트 로직 작성
        // 예: updateReserveUsecase.execute(number);
        // Assertions.assertEquals(expectedWaitingCount, booth.getWaitingTeamCount());
    }

    @Test
    @DisplayName("관리자가 사용자를 호출하는 경우 대기 팀 수가 감소하고 예약 상태가 CALLED로 변경된다.")
    public void calledReserveDecreasesWaitingTeamCountTest() {
        // 관리자가 호출 후 대기 팀 수 감소 테스트 로직 작성
        // 예: calledReserveUseCase.execute(number);
        // Assertions.assertEquals(expectedWaitingCount, booth.getWaitingTeamCount());
    }

    @Test
    @DisplayName("관리자가 사용자를 입장 완료 처리하는 경우, 대기 팀 수는 변경되지 않고 예약 상태가 COMPLETED로 변경된다.")
    public void completeReserveDoesNotChangeWaitingTeamCountTest() {
        // 관리자가 입장 완료 처리 후 대기 팀 수가 변경되지 않는지 테스트 로직 작성
        // 예: completeReserveUseCase.execute(number);
        // Assertions.assertEquals(expectedWaitingCount, booth.getWaitingTeamCount());
    }

    @Test
    @DisplayName("관리자가 늦은 사용자를 입장 완료 처리하는 경우, 대기 팀 수는 변경되지 않고 예약 상태가 COMPLETED로 변경된다.")
    public void completeLateReserveDoesNotChangeWaitingTeamCountTest() {
        // 늦은 사용자 입장 완료 처리 후 대기 팀 수가 변경되지 않는지 테스트 로직 작성
        // 예: completeReserveUseCase.execute(number);
        // Assertions.assertEquals(expectedWaitingCount, booth.getWaitingTeamCount());
    }

    @Test
    @DisplayName("관리자가 사용자를 대기 삭제하는 경우, 예약 상태가 CANCELED로 변경되고 대기 팀 수는 변하지 않는다.")
    public void deleteReserveDoesNotChangeWaitingTeamCountTest() {
        // 관리자가 대기 삭제 후 대기 팀 수가 변경되지 않는지 테스트 로직 작성
        // 예: updateReserveUsecase.execute(number);
        // Assertions.assertEquals(expectedWaitingCount, booth.getWaitingTeamCount());
    }

    @Test
    @DisplayName("사용자는 자신의 예약한 야간 부스에서의 자신의 대기 순번을 확인할 수 있다.")
    public void readReserveTest() {
        // 자신의 예약한 야간 부스에서 대기 순번 확인 테스트 로직 작성
        // 예: readReserveUsecase.execute(number);
        // Assertions.assertEquals(expectedQueueNumber, reserve.getQueueNumber());
    }

    @Test
    @DisplayName("사용자가 예약 취소를 했다면 해당 부스에 대한 자신의 대기 순번을 확인할 수 없다.")
    public void readReserveAfterCancelTest() {
        // 예약 취소 후 대기 순번 확인 시도 테스트 로직 작성
        // 예: readReserveUsecase.execute(number);
        // Assertions.assertThrows(NoSuchReserveException.class, () -> {
        //     readReserveUsecase.execute(number);
        // });
    }

    @Test
    @DisplayName("관리자가 대기자를 호출할 때, 해당 대기자는 예약을 취소하지 않은 WAITING 상태여야만 가능하다.")
    public void calledReserveOnlyWhenWaitingTest() {
        // 관리자가 대기자를 호출할 때 WAITING 상태인지 확인하는 테스트 로직 작성
        // 예: calledReserveUseCase.execute(number);
        // Assertions.assertThrows(InvalidReserveStatusException.class, () -> {
        //     calledReserveUseCase.execute(number);
        // });
    }


}
