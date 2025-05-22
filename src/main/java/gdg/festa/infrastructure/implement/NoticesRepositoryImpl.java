package gdg.festa.infrastructure.implement;

import gdg.festa.domain.entity.Notices;
import gdg.festa.domain.repository.NoticesRepository;
import gdg.festa.infrastructure.jpa.NoticesJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NoticesRepositoryImpl implements NoticesRepository {

    private final NoticesJpaRepository noticesJpaRepository;

    @Override
    public void save(Notices notices) {
        noticesJpaRepository.save(notices);

    }
}
