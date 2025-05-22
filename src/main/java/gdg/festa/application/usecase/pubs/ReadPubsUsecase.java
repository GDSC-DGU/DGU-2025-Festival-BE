package gdg.festa.application.usecase.pubs;

import gdg.festa.application.dto.pub.ReadPubsWaitingUserListResponseDto;
import gdg.festa.core.annotation.UseCase;

import java.util.List;

@UseCase
public interface ReadPubsUsecase {
    List<ReadPubsWaitingUserListResponseDto> execute();
}
