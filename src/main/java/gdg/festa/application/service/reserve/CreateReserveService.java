package gdg.festa.application.service.reserve;

import gdg.festa.application.usecase.reserve.CreateReserveUseCase;
import gdg.festa.domain.entity.Pubs;
import gdg.festa.domain.entity.Reserves;
import gdg.festa.domain.repository.PubsRepository;
import gdg.festa.domain.repository.ReserveRepository;
import gdg.festa.presentation.request.reserve.CreateReserveRequestDto;
import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateReserveService implements CreateReserveUseCase {
    private final ReserveRepository reserveRepository;
    private final PubsRepository pubsRepository;
    @Override
    public Boolean execute(Long boothId, CreateReserveRequestDto createReserveRequestDto) {
        Reserves reserves = reserveRepository.findByPhoneNumberAndReserveStatus(createReserveRequestDto.phoneNumber());

        Pubs pubs = pubsRepository.findById(boothId);

        reserves.updateReserve(
                createReserveRequestDto.attendance(),
                createReserveRequestDto.name(),
                pubs
        );

        return true;
    }
}
