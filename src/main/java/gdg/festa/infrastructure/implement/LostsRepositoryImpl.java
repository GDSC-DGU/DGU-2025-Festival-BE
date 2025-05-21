package gdg.festa.infrastructure.implement;

import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.domain.entity.Losts;
import gdg.festa.domain.entity.Pubs;
import gdg.festa.domain.repository.LostsRepository;
import gdg.festa.infrastructure.jpa.LostsJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LostsRepositoryImpl implements LostsRepository {

    private final LostsJpaRepository lostsJpaRepository;

    @Override
    public Losts save(Losts losts){
        return lostsJpaRepository.save(losts);
    }

    @Override
    public Losts findById(Long lostsId){
        return lostsJpaRepository.findById(lostsId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_LOST));
    }

    @Override
    public void deleteById(Long lostsId){
        lostsJpaRepository.deleteById(lostsId);
    }
}
