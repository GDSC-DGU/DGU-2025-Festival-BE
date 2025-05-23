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

    private final LostImageJpaRepository lostImageJpaRepository;

    @Override
    public void saveAll(List<LostImage> lostImages){
        lostImageJpaRepository.saveAll(lostImages);
    }

//    @Override
//    public List<LostImage> findByLostId(Long lostId){
//        return lostImageJpaRepository.findByLostId(lostId);
//    }

    @Override
    public List<LostImage> findByLost(Lost lost){
        return lostImageJpaRepository.findByLost(lost);
    }

    @Override
    public void deleteByLost(Lost lost){
        lostImageJpaRepository.deleteByLost(lost);
    }

    @Override
    public List<LostImage> findByLostAndDeletedAtIsNull(Lost lost) {
        return lostImageJpaRepository.findByLostAndDeletedAtIsNull(lost);
    }
}
