package gdg.festa.infrastructure.implement;

import gdg.festa.domain.entity.LostImages;
import gdg.festa.domain.entity.Losts;
import gdg.festa.domain.repository.LostImageRepository;
import gdg.festa.domain.repository.LostsRepository;
import gdg.festa.infrastructure.jpa.LostImageJpaRepository;
import gdg.festa.infrastructure.jpa.LostsJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LostImageRepositoryImpl implements LostImageRepository {

    private final LostImageJpaRepository lostsImageJpaRepository;

    @Override
    public void saveAll(List<LostImages> lostImages){
        lostsImageJpaRepository.saveAll(lostImages);
    }

    @Override
    public List<LostImages> findByLostsLostsId(Long lostsId){
        return lostsImageJpaRepository.findByLostsLostsId(lostsId);
    }

    @Override
    public List<LostImages> findByLosts(Losts losts){
        return lostsImageJpaRepository.findByLosts(losts);
    }

    @Override
    public void deleteByLosts(Losts losts){
        lostsImageJpaRepository.deleteByLosts(losts);
    }

    @Override
    public List<LostImages> findByLostsAndDeletedAtIsNull(Losts losts) {
        return lostsImageJpaRepository.findByLostsAndDeletedAtIsNull(losts);
    }
}
