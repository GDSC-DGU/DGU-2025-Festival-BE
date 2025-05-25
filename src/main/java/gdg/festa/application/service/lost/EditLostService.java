package gdg.festa.application.service.lost;

import javax.annotation.PostConstruct;

import gdg.festa.application.mapper.LostImageMapper;
import gdg.festa.application.usecase.lost.EditLostUsecase;
import gdg.festa.core.constant.Constants;
import gdg.festa.core.util.RedisUtil;
import gdg.festa.core.util.S3Util;
import gdg.festa.domain.entity.LostImage;
import gdg.festa.domain.entity.Lost;
import gdg.festa.domain.repository.LostImageRepository;
import gdg.festa.domain.repository.LostRepository;
import gdg.festa.presentation.request.lost.LostRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EditLostService implements EditLostUsecase {

    private final LostRepository lostRepository;
    private final LostImageRepository lostImageRepository;
    private final LostImageMapper lostImageMapper;
    private final S3Util s3Util;
    private final RedisUtil redisUtil;

    @PostConstruct
    public void init() {
        redisUtil.setKeyPrefix(Constants.REDIS_LOST_KEY_PREFIX);
    }

    @Override
    public void execute(Long lostId, LostRequestDto lostRequestDto) {
        Lost lost = lostRepository.findById(lostId);

        lost.setLost(lostRequestDto);

        List<LostImage> oldImages = lostImageRepository.findByLostAndDeletedAtIsNull(lost);
        oldImages.forEach(img -> {
            img.delete();  // deletedAt = now()

        });

        List<String> imageUrls = s3Util.upload(lostRequestDto.images());

        List<LostImage> newImageEntities = imageUrls.stream()
                .map(url -> lostImageMapper.toEntity(url, lost))
                .collect(Collectors.toList());

        lostImageRepository.saveAll(newImageEntities);

        resetLostCache(lost);
    }

    private void resetLostCache(Lost lost) {
        redisUtil.delete(String.valueOf(lost.getLostId()));
        redisUtil.delete("all");
        redisUtil.delete("tag:" + lost.getTag().name());
        log.info("Lost cache reset for ID: {}", lost.getLostId());
    }
}
