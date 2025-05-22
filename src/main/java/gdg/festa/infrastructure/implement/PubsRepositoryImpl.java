package gdg.festa.infrastructure.implement;

import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.domain.entity.Pubs;
import gdg.festa.domain.entity.Reserves;
import gdg.festa.domain.repository.PubsRepository;
import gdg.festa.infrastructure.jpa.PubsJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class PubsRepositoryImpl implements PubsRepository {

    private final PubsJpaRepository pubsJpaRepository;

    @Override
    public Pubs findById(Long id) {
        return pubsJpaRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PUBS));
    }

    @Override
    public List<Pubs> findAll() {
        return pubsJpaRepository.findAll();
    }

    @Override
    public void decreseWaitPeople(Long id) {
        pubsJpaRepository.decreseWaitPeople(id);
    }


}
