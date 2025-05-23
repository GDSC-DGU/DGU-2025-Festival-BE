package gdg.festa.infrastructure.implement;

import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.domain.entity.Pub;
import gdg.festa.domain.repository.PubRepository;
import gdg.festa.infrastructure.jpa.PubJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class PubRepositoryImpl implements PubRepository {

    private final PubJpaRepository pubJpaRepository;

    @Override
    public Pub findById(Long id) {
        return pubJpaRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PUBS));
    }

    @Override
    public List<Pub> findAll() {
        return pubJpaRepository.findAll();
    }

    @Override
    public void decreseWaitPeople(Long id) {
        pubJpaRepository.decreseWaitPeople(id);
    }


}
