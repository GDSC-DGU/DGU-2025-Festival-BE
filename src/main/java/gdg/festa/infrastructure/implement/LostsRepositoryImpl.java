package gdg.festa.infrastructure.implement;

import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.domain.entity.Lost;
import gdg.festa.domain.repository.LostsRepository;
import gdg.festa.infrastructure.jpa.LostsJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LostsRepositoryImpl implements LostsRepository {

    private final LostsJpaRepository lostsJpaRepository;

    @Override
    public Lost save(Lost lost){
        return lostsJpaRepository.save(lost);
    }

    @Override
    public Lost findById(Long lostsId){
        return lostsJpaRepository.findById(lostsId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_LOST));
    }

    @Override
    public void deleteById(Long lostsId){
        lostsJpaRepository.deleteById(lostsId);
    }
}
