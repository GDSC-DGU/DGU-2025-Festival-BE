package gdg.festa.infrastructure.implement;

import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.domain.entity.Lost;
import gdg.festa.domain.repository.LostRepository;
import gdg.festa.infrastructure.jpa.LostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LostRepositoryImpl implements LostRepository {

    private final LostJpaRepository lostJpaRepository;

    @Override
    public Lost save(Lost lost){
        return lostJpaRepository.save(lost);
    }

    @Override
    public Lost findById(Long lostsId){
        return lostJpaRepository.findById(lostsId)
            .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_LOST));
    }

    @Override
    public void deleteById(Long lostsId){
        lostJpaRepository.deleteById(lostsId);
    }
}
