package gdg.festa.infrastructure.implement;

import gdg.festa.domain.entity.LostImage;
import gdg.festa.domain.entity.Lost;
import gdg.festa.domain.repository.LostImageRepository;
import gdg.festa.infrastructure.jpa.LostImageJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LostImageRepositoryImpl implements LostImageRepository {

    private final LostImageJpaRepository lostsImageJpaRepository;

    @Override
    public void saveAll(List<LostImage> lostImages){
        lostsImageJpaRepository.saveAll(lostImages);
    }

    @Override
    public List<LostImage> findByLostsLostsId(Long lostsId){
        return lostsImageJpaRepository.findByLostsLostsId(lostsId);
    }

    @Override
    public List<LostImage> findByLosts(Lost lost){
        return lostsImageJpaRepository.findByLosts(lost);
    }

    @Override
    public void deleteByLosts(Lost lost){
        lostsImageJpaRepository.deleteByLosts(lost);
    }

    @Override
    public List<LostImage> findByLostsAndDeletedAtIsNull(Lost lost) {
        return lostsImageJpaRepository.findByLostsAndDeletedAtIsNull(lost);
    }
}
