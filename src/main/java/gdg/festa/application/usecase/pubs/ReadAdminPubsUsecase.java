package gdg.festa.application.usecase.pubs;

import gdg.festa.application.dto.pubs.ReadPubWaitingUserListResponseDto;
import gdg.festa.core.annotation.UseCase;

import java.util.UUID;

@UseCase
public interface ReadAdminPubsUsecase {

    ReadPubWaitingUserListResponseDto execute(UUID pubAdminId);
}
