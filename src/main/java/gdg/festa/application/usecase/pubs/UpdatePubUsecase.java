package gdg.festa.application.usecase.pubs;

import gdg.festa.core.annotation.UseCase;

import java.util.UUID;

@UseCase
public interface UpdatePubUsecase {
    Boolean execute(UUID id, String pubsStatus);
}
