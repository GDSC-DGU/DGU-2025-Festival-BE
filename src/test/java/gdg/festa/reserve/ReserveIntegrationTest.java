package gdg.festa.reserve;

import gdg.festa.IntegrationTestContainer;
import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.core.util.FcmUtil;
import gdg.festa.domain.entity.Pub;
import gdg.festa.domain.entity.Reserve;
import gdg.festa.domain.type.PubStatus;
import gdg.festa.domain.type.ReserveStatus;
import gdg.festa.infrastructure.redis.SmsCertification;
import gdg.festa.presentation.request.auth.LoginRequestDto;
import gdg.festa.presentation.request.reserve.CompletedReserveRequestDto;
import gdg.festa.presentation.request.reserve.CreateReserveRequestDto;
import gdg.festa.presentation.request.sms.SmsVerifyRequestDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@Rollback
@Transactional
public class ReserveIntegrationTest extends IntegrationTestContainer {

    @MockitoBean
    private SmsCertification smsCertification;

    @MockitoBean
    private FcmUtil fcmUtil;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        // 인증 키가 존재한다고 하고, 발급 번호도 일치하도록 stub
        given(smsCertification.hasKey(anyString())).willReturn(true);
        given(smsCertification.getSmsCertification(anyString()))
                .willReturn(TEST_CERTIFICATION_CODE);
        given(fcmUtil.sendMessage(anyString(), anyString(), anyString(), any()))
                .willReturn(true);
    }

    @AfterEach
    public void tearDown() {
        // Truncate tables to reset AUTO_INCREMENT sequences
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0");
        jdbcTemplate.execute("TRUNCATE TABLE reserves");
        jdbcTemplate.execute("TRUNCATE TABLE pubs");
        jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1");
    }

    @Test
    @DisplayName("전화번호 인증을 마친 사용자는 만석인 야간 부스에 예약을 할 수 있다.")
    public void createReserveTest() {
        // given
        makeExamplePubs();
        Reserve reserve = verifyUser();

        pubRepository.findAll().forEach(pub -> {
            System.out.println(pub.getPubId() + " " + pub.getPubStatus());
        });

        // when
        createReserveUseCase.execute(
                BOOTH_FULL_ID,
                new CreateReserveRequestDto(
                        TEST_USER_BROWSER_TOKEN,
                        TEST_USER_PHONE,
                        TEST_USER_NAME,
                        5L
                )
        );

        // then
        assertThat(reserve.getReserveStatus()).isEqualTo(ReserveStatus.WAITING);
        assertThat(reserve.getPub().getWaitPeople())
                .isGreaterThan(0L)
                .isEqualTo(1L);
        assertThat(reserve.getPub().getPubStatus()).isEqualTo(PubStatus.FULL);
    }

    @Test
    @DisplayName("어느 야간 부스에 예약을 이미 한 사용자는 해당 부스와 다른 부스에 다시 예약을 할 수 없다.")
    public void createReserveFailTest() {
        // given
        makeExamplePubs();
        Reserve reserve = verifyUser();

        pubRepository.findAll().forEach(pub -> {
            System.out.println(pub.getPubId() + " " + pub.getPubStatus());
        });

        createReserveUseCase.execute(
                BOOTH_FULL_ID,
                new CreateReserveRequestDto(
                        TEST_USER_BROWSER_TOKEN,
                        TEST_USER_PHONE,
                        TEST_USER_NAME,
                        5L
                )
        );

        // when
        CustomException exception = assertThrows(CustomException.class, () -> {
            createReserveUseCase.execute(
                    BOOTH_FULL_ID_2,
                    new CreateReserveRequestDto(
                            TEST_USER_BROWSER_TOKEN,
                            TEST_USER_PHONE,
                            TEST_USER_NAME,
                            3L
                    )
            );
        });

        assertThat(exception.getMessage()).isEqualTo(ErrorCode.CONFLICT_RESERVE.getMessage());
    }

    @Test
    @DisplayName("예약을 취소한 사용자는 해당 부스와 다른 부스에 다시 예약을 할 수 있다.")
    public void cancelReserveTest() {
        // given
        makeExamplePubs();
        Reserve reserve = verifyUser();
        createReserveUseCase.execute(
                BOOTH_FULL_ID,
                new CreateReserveRequestDto(
                        TEST_USER_BROWSER_TOKEN,
                        TEST_USER_PHONE,
                        TEST_USER_NAME,
                        5L
                )
        );
        assertThat(reserve.getReserveStatus()).isEqualTo(ReserveStatus.WAITING);

        updateReserveUsecase.execute(TEST_USER_PHONE);

        // when
        Reserve newReserve = verifyUser();

        createReserveUseCase.execute(
                BOOTH_FULL_ID_2,
                new CreateReserveRequestDto(
                        TEST_USER_BROWSER_TOKEN,
                        TEST_USER_PHONE,
                        TEST_USER_NAME,
                        3L
                )
        );

        // then
        newReserve = reserveRepository.findByPhoneNumberAndReserveStatus(
                TEST_USER_PHONE, ReserveStatus.WAITING
        );

        assertThat(newReserve.getReserveId()).isNotEqualTo(reserve.getReserveId());
        assertThat(newReserve.getPub().getWaitPeople())
                .isEqualTo(1L);
        assertThat(reserve.getPub().getWaitPeople())
                .isEqualTo(0L);

        // when
        updateReserveUsecase.execute(TEST_USER_PHONE);
        Reserve newSecondReserve = verifyUser();

        createReserveUseCase.execute(
                BOOTH_FULL_ID,
                new CreateReserveRequestDto(
                        TEST_USER_BROWSER_TOKEN,
                        TEST_USER_PHONE,
                        TEST_USER_NAME,
                        7L
                )
        );
        // then
        newSecondReserve = reserveRepository.findByPhoneNumberAndReserveStatus(
                TEST_USER_PHONE, ReserveStatus.WAITING
        );

        assertThat(newSecondReserve.getReserveId()).isNotEqualTo(newReserve.getReserveId());
        assertThat(newSecondReserve.getPub().getWaitPeople())
                .isEqualTo(1L);
        assertThat(newReserve.getPub().getWaitPeople())
                .isEqualTo(0L);

    }

    @Test
    @DisplayName("예약을 취소한 후, 다시 휴대폰 인증을 시도하는 경우 새로운 예약이 생성되고 기존 예약은 취소된 히스토리로 남아있다.")
    public void createReserveSuccessWhenBoothAvailableTest() {
        // given
        makeExamplePubs();
        Reserve reserve = verifyUser();
        createReserveUseCase.execute(
                BOOTH_FULL_ID,
                new CreateReserveRequestDto(
                        TEST_USER_BROWSER_TOKEN,
                        TEST_USER_PHONE,
                        TEST_USER_NAME,
                        5L
                )
        );
        assertThat(reserve.getReserveStatus()).isEqualTo(ReserveStatus.WAITING);
        updateReserveUsecase.execute(TEST_USER_PHONE);
        // when
        Reserve newReserve = verifyUser();
        // then
        assertThat(newReserve.getReserveId()).isNotEqualTo(reserve.getReserveId());
        assertThat(newReserve.getReserveStatus()).isEqualTo(ReserveStatus.ENABLED);
        assertThat(reserve.getReserveStatus()).isEqualTo(ReserveStatus.CANCELED);
    }

    @Test
    @DisplayName("예약을 원하는 부스가 바로 입장 가능한 상태라면 예약을 할 수 없다.")
    public void createReserveFailWhenBoothAvailableTest() {
        // given
        makeExamplePubs();
        Reserve reserve = verifyUser();

        // when
        CustomException exception = assertThrows(CustomException.class, () -> {
            createReserveUseCase.execute(
                    BOOTH_AVAILABLE_ID,
                    new CreateReserveRequestDto(
                            TEST_USER_BROWSER_TOKEN,
                            TEST_USER_PHONE,
                            TEST_USER_NAME,
                            5L
                    )
            );
        });

        // then
        assertThat(exception.getMessage()).isEqualTo(ErrorCode.ACCESS_AVAILABLE.getMessage());
    }

    @Test
    @DisplayName("예약을 원하는 부스가 만석인 상태라면 예약을 할 수 있다.")
    public void createReserveSuccessWhenBoothFullTest() {
        // given
        makeExamplePubs();
        Reserve reserve = verifyUser();

        // when
        createReserveUseCase.execute(
                BOOTH_FULL_ID,
                new CreateReserveRequestDto(
                        TEST_USER_BROWSER_TOKEN,
                        TEST_USER_PHONE,
                        TEST_USER_NAME,
                        5L
                )
        );

        // then
        assertThat(reserve.getReserveStatus()).isEqualTo(ReserveStatus.WAITING);
    }

    @Test
    @DisplayName("예약을 원하는 부스가 준비 중이면 예약을 할 수 없다.")
    public void createReserveFailWhenBoothPreparingOrClosedTest() {
        // given
        makeExamplePubs();
        Reserve reserve = verifyUser();

        // when
        CustomException exception = assertThrows(CustomException.class, () -> {
            createReserveUseCase.execute(
                    BOOTH_PREPARING_ID,
                    new CreateReserveRequestDto(
                            TEST_USER_BROWSER_TOKEN,
                            TEST_USER_PHONE,
                            TEST_USER_NAME,
                            5L
                    )
            );
        });

        // then
        assertThat(exception.getMessage()).isEqualTo(ErrorCode.ACCESS_STOP.getMessage());
    }

    @Test
    @DisplayName("예약을 원하는 부스가 끝났다면 예약을 할 수 없다.")
    public void createReserveFailWhenBoothEndTest() {
        // given
        makeExamplePubs();
        Reserve reserve = verifyUser();

        // when
        CustomException exception = assertThrows(CustomException.class, () -> {
            createReserveUseCase.execute(
                    BOOTH_END_ID,
                    new CreateReserveRequestDto(
                            TEST_USER_BROWSER_TOKEN,
                            TEST_USER_PHONE,
                            TEST_USER_NAME,
                            5L
                    )
            );
        });

        // then
        assertThat(exception.getMessage()).isEqualTo(ErrorCode.ACCESS_STOP.getMessage());
    }

    @Test
    @DisplayName("예약 시 해당 부스의 대기 팀 수가 증가한다.")
    public void reserveIncreasesWaitingTeamCountTest() {
        // given
        makeExamplePubs();
        Reserve reserve = verifyUser();

        // when
        createReserveUseCase.execute(
                BOOTH_FULL_ID,
                new CreateReserveRequestDto(
                        TEST_USER_BROWSER_TOKEN,
                        TEST_USER_PHONE,
                        TEST_USER_NAME,
                        5L
                )
        );

        // then
        assertThat(reserve.getPub().getWaitPeople())
                .isGreaterThan(0L)
                .isEqualTo(1L);
        assertThat(pubRepository.findById(BOOTH_FULL_ID).getWaitPeople()).isEqualTo(1L);
    }

    @Test
    @DisplayName("예약 취소 시 해당 부스의 대기 팀 수가 감소하고 예약 상태가 CANCELED로 변경된다.")
    public void cancelReserveDecreasesWaitingTeamCountTest() {
        // given
        makeExamplePubs();
        Reserve reserve = verifyUser();
        createReserveUseCase.execute(
                BOOTH_FULL_ID,
                new CreateReserveRequestDto(
                        TEST_USER_BROWSER_TOKEN,
                        TEST_USER_PHONE,
                        TEST_USER_NAME,
                        5L
                )
        );
        assertThat(reserve.getReserveStatus()).isEqualTo(ReserveStatus.WAITING);
        assertThat(reserve.getPub().getWaitPeople()).isEqualTo(1L);

        // when
        updateReserveUsecase.execute(TEST_USER_PHONE);

        // then
        assertThat(reserve.getReserveStatus()).isEqualTo(ReserveStatus.CANCELED);
        assertThat(pubRepository.findById(BOOTH_FULL_ID).getWaitPeople()).isEqualTo(0L);
    }

    @Test
    @DisplayName("관리자가 사용자를 호출하는 경우 대기 팀 수가 감소하고 예약 상태가 CALLED로 변경된다.")
    public void calledReserveDecreasesWaitingTeamCountTest() {
        // given
        makeExamplePubs();
        Reserve reserve = verifyUser();
        createReserveUseCase.execute(
                BOOTH_FULL_ID,
                new CreateReserveRequestDto(
                        TEST_USER_BROWSER_TOKEN,
                        TEST_USER_PHONE,
                        TEST_USER_NAME,
                        5L
                )
        );
        assertThat(reserve.getReserveStatus()).isEqualTo(ReserveStatus.WAITING);
        assertThat(reserve.getPub().getWaitPeople()).isEqualTo(1L);

        // when
        calledReserveUseCase.execute(new CompletedReserveRequestDto(
                reserve.getReserveId()
        ));

        // then
        assertThat(reserve.getReserveStatus()).isEqualTo(ReserveStatus.CALLED);
        assertThat(reserve.getPub().getWaitPeople()).isEqualTo(0L);
        assertThat(pubRepository.findById(BOOTH_FULL_ID).getWaitPeople()).isEqualTo(0L);
    }

    @Test
    @DisplayName("관리자가 사용자를 입장 완료 처리하는 경우, 대기 팀 수는 변경되지 않고 예약 상태가 COMPLETED로 변경된다.")
    public void completeReserveDoesNotChangeWaitingTeamCountTest() {
        // given
        UUID pubAdminId = registerUseCase.execute(
                new LoginRequestDto(
                        PUB_ADMIN_LOGIN_ID,
                        PUB_ADMIN_LOGIN_PASSWORD,
                        "ADPUB"
                )
        );
        makeExamplePubs();
        Reserve reserve = verifyUser();
        createReserveUseCase.execute(
                BOOTH_FULL_ID,
                new CreateReserveRequestDto(
                        TEST_USER_BROWSER_TOKEN,
                        TEST_USER_PHONE,
                        TEST_USER_NAME,
                        5L
                )
        );
        assertThat(reserve.getReserveStatus()).isEqualTo(ReserveStatus.WAITING);
        assertThat(reserve.getPub().getWaitPeople()).isEqualTo(1L);

        calledReserveUseCase.execute(new CompletedReserveRequestDto(
                reserve.getReserveId()));
        assertThat(reserve.getReserveStatus()).isEqualTo(ReserveStatus.CALLED);
        assertThat(reserve.getPub().getWaitPeople()).isEqualTo(0L);

        // when
        completeReserveUseCase.execute(
                pubAdminId,
                new CompletedReserveRequestDto(
                        reserve.getReserveId()
                )
        );

        // then
        assertThat(reserve.getReserveStatus()).isEqualTo(ReserveStatus.COMPLETED);
        assertThat(reserve.getPub().getWaitPeople()).isEqualTo(0L);
    }

    @Test
    @Disabled
    @DisplayName("관리자가 늦은 사용자를 입장 완료 처리하는 경우, 대기 팀 수는 변경되지 않고 예약 상태가 COMPLETED로 변경된다.")
    public void completeLateReserveDoesNotChangeWaitingTeamCountTest() {
        // 늦은 사용자 입장 완료 처리 후 대기 팀 수가 변경되지 않는지 테스트 로직 작성
        // 예: completeReserveUseCase.execute(number);
        // Assertions.assertEquals(expectedWaitingCount, booth.getWaitingTeamCount());
    }

    @Test
    @Disabled
    @DisplayName("관리자가 늦은 사용자를 대기 삭제하는 경우, 예약 상태가 CANCELED로 변경되고 대기 팀 수는 변하지 않는다.")
    public void deleteReserveDoesNotChangeWaitingTeamCountTest() {
        // 관리자가 대기 삭제 후 대기 팀 수가 변경되지 않는지 테스트 로직 작성
        // 예: updateReserveUsecase.execute(number);
        // Assertions.assertEquals(expectedWaitingCount, booth.getWaitingTeamCount());
    }

    @Test
    @Disabled
    @DisplayName("관리자가 예약 중인 사용자를 대기 삭제하는 경우, 예약 상태가 CANCELED로 변경되고 대기 팀 수가 감소한다.")
    public void deleteReserveWhenWaitingTest() {
        // 관리자가 대기 삭제 후 대기 팀 수 감소 테스트 로직 작성
        // 예: updateReserveUsecase.execute(number);
        // Assertions.assertEquals(expectedWaitingCount, booth.getWaitingTeamCount());
    }

    @Test
    @Disabled
    @DisplayName("사용자는 자신의 예약한 야간 부스에서의 자신의 대기 순번을 확인할 수 있다.")
    public void readReserveTest() {
        // 자신의 예약한 야간 부스에서 대기 순번 확인 테스트 로직 작성
        // 예: readReserveUsecase.execute(number);
        // Assertions.assertEquals(expectedQueueNumber, reserve.getQueueNumber());
    }

    @Test
    @Disabled
    @DisplayName("사용자가 예약 취소를 했다면 해당 부스에 대한 자신의 대기 순번을 확인할 수 없다.")
    public void readReserveAfterCancelTest() {
        // 예약 취소 후 대기 순번 확인 시도 테스트 로직 작성
        // 예: readReserveUsecase.execute(number);
        // Assertions.assertThrows(NoSuchReserveException.class, () -> {
        //     readReserveUsecase.execute(number);
        // });
    }

    @Test
    @Disabled
    @DisplayName("관리자가 대기자를 호출할 때, 해당 대기자는 예약을 취소하지 않은 WAITING 상태여야만 가능하다.")
    public void calledReserveOnlyWhenWaitingTest() {
        // 관리자가 대기자를 호출할 때 WAITING 상태인지 확인하는 테스트 로직 작성
        // 예: calledReserveUseCase.execute(number);
        // Assertions.assertThrows(InvalidReserveStatusException.class, () -> {
        //     calledReserveUseCase.execute(number);
        // });
    }

    @Test
    @DisplayName("사용자가 전화번호 인증을 하지 않은 경우 예약을 할 수 없다.")
    public void createReserveFailWhenNotVerifiedTest() {
        // given
        makeExamplePubs();

        // when
        CustomException exception = assertThrows(CustomException.class, () -> {
            createReserveUseCase.execute(
                    BOOTH_FULL_ID,
                    new CreateReserveRequestDto(
                            TEST_USER_BROWSER_TOKEN,
                            TEST_USER_PHONE,
                            TEST_USER_NAME,
                            5L
                    )
            );
        });

        // then
        assertThat(exception.getMessage()).isEqualTo(ErrorCode.NOT_FOUND_VERIFY.getMessage());
    }

    @Test
    @DisplayName("사용자가 전화번호 인증을 마치고, 다시 전화번호 인증을 하면 값이 추가되지 않는다.")
    public void smsVerifyTwiceTest() {
        // given
        makeExamplePubs();
        Reserve reserve = verifyUser();
        reserveRepository.findById(reserve.getReserveId());

        // when
        Reserve noAddReserve = smsVertifyUseCase.execute(
                new SmsVerifyRequestDto(
                        TEST_USER_PHONE,
                        TEST_CERTIFICATION_CODE,
                        TEST_USER_BROWSER_TOKEN
                )
        );

        assertThat(noAddReserve.getReserveId()).isEqualTo(reserve.getReserveId());
    }

    private void makeExamplePubs() {
        pubRepository.save(new Pub(
                null,
                "testBooth1",
                "test location",
                "test Menus",
                "test picture",
                "test Note",
                0L, // 대기 인원 수
                PubStatus.AVAILABLE // 부스 상태
        ));

        pubRepository.save(new Pub(
                null,
                "testBooth2",
                "test location",
                "test Menus",
                "test picture",
                "test Note",
                0L, // 대기 인원 수
                PubStatus.FULL // 부스 상태
        ));

        pubRepository.save(new Pub(
                null,
                "testBooth 3",
                "test location",
                "test Menus",
                "test picture",
                "test Note",
                0L, // 대기 인원 수
                PubStatus.PREPARING // 부스 상태
        ));

        pubRepository.save(new Pub(
                null,
                "testBooth 4",
                "test location",
                "test Menus",
                "test picture",
                "test Note",
                0L, // 대기 인원 수
                PubStatus.END // 부스 상태
        ));

        pubRepository.save(new Pub(
                null,
                "testBooth5",
                "test location",
                "test Menus",
                "test picture",
                "test Note",
                0L, // 대기 인원 수
                PubStatus.FULL // 부스 상태
        ));
    }

    private Reserve verifyUser() {
        Reserve reserve = smsVertifyUseCase.execute(
                new SmsVerifyRequestDto(
                        TEST_USER_PHONE,
                        TEST_CERTIFICATION_CODE,
                        TEST_USER_BROWSER_TOKEN
                )
        );

        assertThat(reserve.getReserveStatus()).isEqualTo(ReserveStatus.ENABLED);

        return reserve;
    }

}


