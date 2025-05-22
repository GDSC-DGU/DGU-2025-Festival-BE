package gdg.festa.infrastructure.implement;

import gdg.festa.domain.entity.Notice;
import gdg.festa.domain.repository.NoticesRepository;
import gdg.festa.infrastructure.jpa.NoticesJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NoticesRepositoryImpl implements NoticesRepository {

    private final NoticesJpaRepository noticesJpaRepository;

    @Override
    public void save(Notice notice) {
        noticesJpaRepository.save(notice);

    }
}
