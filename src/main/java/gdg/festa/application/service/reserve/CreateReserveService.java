package gdg.festa.application.service.reserve;

import gdg.festa.application.usecase.reserve.CreateReserveUseCase;
import gdg.festa.domain.entity.Pub;
import gdg.festa.domain.entity.Reserve;
import gdg.festa.domain.repository.PubsRepository;
import gdg.festa.domain.repository.ReserveRepository;
import gdg.festa.presentation.request.reserve.CreateReserveRequestDto;
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
        Reserve reserve = reserveRepository.findByPhoneNumberAndReserveStatus(createReserveRequestDto.phoneNumber());

        Pub pub = pubsRepository.findById(boothId);

        reserve.updateReserve(
                createReserveRequestDto.attendance(),
                createReserveRequestDto.name(),
                pub
        );

        return true;
    }
}
