package gdg.festa.application.service.pubs;

import gdg.festa.application.dto.pubs.ReadPubsWaitingUserListResponseDto;
import gdg.festa.application.usecase.pubs.ReadPubsUsecase;
import gdg.festa.domain.entity.Pub;
import gdg.festa.domain.repository.PubsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReadPubsService implements ReadPubsUsecase {
    private final PubsRepository pubsRepository;

    @Override
    public List<ReadPubsWaitingUserListResponseDto> execute() {
        List<Pub> pubList = pubsRepository.findAll();
        return pubList.stream()
                .map(pubs -> ReadPubsWaitingUserListResponseDto.builder()
                        .pubsId(pubs.getPubId())
                        .waitTeam(pubs.getWaitPeople())
                        .build())
                .collect(Collectors.toList());
    }
}

