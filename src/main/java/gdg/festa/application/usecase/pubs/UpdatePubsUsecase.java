package gdg.festa.application.usecase.pubs;

import gdg.festa.core.annotation.UseCase;
import gdg.festa.domain.type.PubsStatus;

import java.util.UUID;

@UseCase
public interface UpdatePubsUsecase {
    Boolean execute(UUID id, PubsStatus pubsStatus);
}
