package gdg.festa;

import gdg.festa.application.usecase.reserve.*;
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

	// 커스텀 컨테이너 클래스들
	public static class KMySQLContainer extends MySQLContainer<KMySQLContainer> {
		public KMySQLContainer(DockerImageName dockerImageName) {
			super(dockerImageName);
		}
	}
}