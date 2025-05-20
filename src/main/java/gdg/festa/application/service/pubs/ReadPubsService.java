package gdg.festa.application.service.pubs;

import gdg.festa.application.dto.pubs.ReadPubsWaitingUserListResponseDto;
import gdg.festa.application.usecase.pubs.ReadPubsUsecase;
import gdg.festa.domain.entity.Pubs;
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
        List<Pubs> pubsList = pubsRepository.findAll();
        return pubsList.stream()
                .map(pubs -> ReadPubsWaitingUserListResponseDto.builder()
                        .pubsId(pubs.getPubsId())
                        .waitTeam(pubs.getWaitPeople())
                        .build())
                .collect(Collectors.toList());
    }
}

