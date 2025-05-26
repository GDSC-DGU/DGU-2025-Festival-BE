package gdg.festa;

import gdg.festa.application.usecase.reserve.*;
import gdg.festa.application.usecase.sms.SmsCertifyUseCase;
import gdg.festa.application.usecase.sms.SmsVertifyUseCase;
import gdg.festa.domain.repository.PubRepository;
import gdg.festa.domain.repository.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;


@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
@DirtiesContext
@AutoConfigureMockMvc
public abstract class IntegrationTestContainer {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected CalledReserveUseCase calledReserveUseCase;

    @Autowired
    protected CompleteReserveUseCase completeReserveUseCase;

    @Autowired
    protected CreateReserveUseCase createReserveUseCase;

    @Autowired
    protected ReadReserveUsecase readReserveUsecase;

    @Autowired
    protected UpdateReserveUsecase updateReserveUsecase;

    @Autowired
    protected SmsCertifyUseCase smsCertifyUseCase;

    @Autowired
    protected SmsVertifyUseCase smsVertifyUseCase;

    @Autowired
    protected ReserveRepository reserveRepository;

    @Autowired
    protected PubRepository pubRepository;

    protected static final String TEST_USER_NAME = "test-user";
    protected static final String TEST_USER_PHONE = "01012345678";
    protected static final String TEST_CERTIFICATION_CODE = "1234"; // SMS 인증 코드
    protected static final String TEST_USER_BROWSER_TOKEN = "testBrowserToken";
    protected static final Long BOOTH_AVAILABLE_ID = 1L; // 예약 가능한 부스 ID
    protected static final Long BOOTH_FULL_ID = 2L; // 만석인 부스 ID
    protected static final Long BOOTH_PREPARING_ID = 3L; // 준비 중인 부스 ID
    protected static final Long BOOTH_END_ID = 4L; // 종료된 부스 ID
    protected static final Long BOOTH_FULL_ID_2 = 5L; // 만석인 부스 ID 2

    @Container
    public static MySQLContainer<?> mySQLContainer =
            new KMySQLContainer(DockerImageName.parse("mysql:8.0.40"))
                    .withReuse(true);

    @DynamicPropertySource
    public static void setProperties(DynamicPropertyRegistry registry) {
        System.out.println("MySQL Container URL: " + mySQLContainer.getJdbcUrl());
        System.out.println("MySQL Container isRunning: " + mySQLContainer.isRunning());

        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);

    }

}