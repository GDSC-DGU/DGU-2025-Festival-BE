package gdg.festa.application.service.lost;

import gdg.festa.application.mapper.LostImageMapper;
import gdg.festa.application.mapper.LostMapper;
import gdg.festa.application.usecase.lost.RegistLostUsecase;
import gdg.festa.core.constant.Constants;
import gdg.festa.core.exception.CustomException;
import gdg.festa.core.exception.ErrorCode;
import gdg.festa.core.util.RedisUtil;
import gdg.festa.core.util.S3Util;
import gdg.festa.domain.entity.LostImage;
import gdg.festa.domain.entity.Lost;
import gdg.festa.domain.repository.LostImageRepository;
import gdg.festa.domain.repository.LostRepository;
import gdg.festa.presentation.request.lost.CreateLostRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RegisterLostService implements RegistLostUsecase {
    private final LostRepository lostRepository;
    private final LostImageRepository lostImageRepository;
    private final LostMapper lostMapper;
    private final LostImageMapper lostImageMapper;
    private final S3Util s3Util;
    private final RedisUtil redisUtil;

    @PostConstruct
    public void init() {
        redisUtil.setKeyPrefix(Constants.REDIS_LOST_KEY_PREFIX);
    }

    @Override
    public void execute(CreateLostRequestDto createLostRequestDto) {

        List<String> imageUrls = s3Util.upload(createLostRequestDto.images());

        Lost savelost;
        Lost lost = lostMapper.toEntity(createLostRequestDto);
        try{
            savelost = lostRepository.save(lost);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NOT_SAVE_PROPER);
        }

        List<LostImage> lostImages = imageUrls.stream()
                .map(imageUrl -> {
                    return lostImageMapper.toEntity(imageUrl, savelost);
                }).collect(Collectors.toList());

        lostImageRepository.saveAll(lostImages);

        resetLostCache(lost);
    }

    private void resetLostCache(Lost lost) {
        redisUtil.delete(String.valueOf(lost.getLostId()));
        redisUtil.delete("all");
        redisUtil.delete("tag:" + lost.getTag().name());
        log.info("Lost cache reset for ID: {}", lost.getLostId());
    }
}
