package gdg.festa.application.service.lost;

import gdg.festa.application.usecase.lost.RemoveLostUsecase;
import gdg.festa.core.constant.Constants;
import gdg.festa.core.util.RedisUtil;
import gdg.festa.domain.entity.LostImage;
import gdg.festa.domain.entity.Lost;
import gdg.festa.domain.repository.LostImageRepository;
import gdg.festa.domain.repository.LostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RemoveLostService implements RemoveLostUsecase {

    private final LostRepository lostRepository;
    private final LostImageRepository lostImageRepository;
    private final RedisUtil redisUtil;

    @PostConstruct
    public void init() {
        redisUtil.setKeyPrefix(Constants.REDIS_LOST_KEY_PREFIX);
    }

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

        resetLostCache(getLost);
    }

    private void resetLostCache(Lost lost) {
        redisUtil.delete(String.valueOf(lost.getLostId()));
        redisUtil.delete("all");
        redisUtil.delete("tag:" + lost.getTag().name());
        log.info("Lost cache reset for ID: {}", lost.getLostId());
    }
}
