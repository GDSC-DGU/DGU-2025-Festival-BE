package gdg.festa.application.service.Lost;

import gdg.festa.application.usecase.lost.RemoveLostUsecase;
import gdg.festa.domain.entity.LostImage;
import gdg.festa.domain.entity.Lost;
import gdg.festa.domain.repository.LostImageRepository;
import gdg.festa.domain.repository.LostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RemoveLostService implements RemoveLostUsecase {

    private final LostRepository lostRepository;
    private final LostImageRepository lostImageRepository;

    @Override
    public void execute(Long lostId){
        Lost getLost = lostRepository.findById(lostId);

        // 관련 이미지들도 Soft Delete
        List<LostImage> lostImages = lostImageRepository.findByLostAndDeletedAtIsNull(getLost);
        for (LostImage image : lostImages) {
            image.delete(); // deletedAt에 시간 설정
        }

        // Losts 자체도 Soft Delete
        getLost.delete(); // deletedAt = now()
    }
}
