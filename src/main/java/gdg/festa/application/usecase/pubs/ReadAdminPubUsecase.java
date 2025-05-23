package gdg.festa.application.usecase.pubs;

import gdg.festa.application.dto.pub.ReadPubWaitingUserListResponseDto;
import gdg.festa.core.annotation.UseCase;

import java.util.UUID;

@UseCase
public interface ReadAdminPubUsecase {

    ReadPubWaitingUserListResponseDto execute(UUID pubAdminId);
}
