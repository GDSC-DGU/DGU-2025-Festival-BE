package gdg.festa;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

public class KMySQLContainer extends MySQLContainer<KMySQLContainer> {
    public KMySQLContainer(DockerImageName dockerImageName) {
        super(dockerImageName);
    }
}
