package gdg.festa.infrastructure.implement;

import gdg.festa.domain.entity.Losts;
import gdg.festa.domain.entity.Pubs;
import gdg.festa.domain.repository.LostsRepository;
import gdg.festa.infrastructure.jpa.LostsJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LostsRepositoryImpl implements LostsRepository {

    private final LostsJpaRepository lostsJpaRepository;

    @Override
    public Losts save(Losts losts){
        return lostsJpaRepository.save(losts);
    }
}
