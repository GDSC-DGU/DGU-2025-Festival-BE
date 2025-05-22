package gdg.festa.application.service.Losts;

import gdg.festa.application.usecase.Losts.RemoveLostsUsecase;
import gdg.festa.domain.entity.LostImages;
import gdg.festa.domain.entity.Losts;
import gdg.festa.domain.repository.LostImageRepository;
import gdg.festa.domain.repository.LostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RemoveLostService implements RemoveLostsUsecase {

    private final LostsRepository lostsRepository;
    private final LostImageRepository lostImageRepository;

    @Override
    public void execute(Long lostsId){
        Losts getLost = lostsRepository.findById(lostsId);

        // 관련 이미지들도 Soft Delete
        List<LostImages> lostImages = lostImageRepository.findByLostsAndDeletedAtIsNull(getLost);
        for (LostImages image : lostImages) {
            image.delete(); // deletedAt에 시간 설정
        }

        // Losts 자체도 Soft Delete
        getLost.delete(); // deletedAt = now()
    }
}
