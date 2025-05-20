package gdg.festa.application.service.pubs;

import gdg.festa.application.usecase.pubs.UpdatePubsUsecase;
import gdg.festa.domain.entity.Pubs;
import gdg.festa.domain.entity.PubsAdmin;
import gdg.festa.domain.repository.PubsAdminRepository;
import gdg.festa.domain.repository.PubsRepository;
import gdg.festa.domain.type.PubsStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdatePubService implements UpdatePubsUsecase {

    private final PubsAdminRepository pubsAdminRepository;
    private final PubsRepository pubsRepository;
    @Override
    public Boolean execute(UUID id, PubsStatus pubsStatus) {


        PubsAdmin pubsAdmin = pubsAdminRepository.findById(id);

        Pubs pubs = pubsRepository.findById(pubsAdmin.getPubs().getPubsId());


        /*
        *  PREPAREING 으로 바뀌는 경우, 대기 인원들에게 알람 전송하기
        *
        *
        *
        *  */

        pubs.updateState(pubsStatus);

        return true;
    }

}
