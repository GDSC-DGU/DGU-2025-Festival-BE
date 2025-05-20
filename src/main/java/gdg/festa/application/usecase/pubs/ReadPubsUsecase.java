package gdg.festa.application.usecase.pubs;

import gdg.festa.application.dto.pubs.ReadPubsWaitingUserListResponseDto;
import gdg.festa.core.annotation.UseCase;
import gdg.festa.domain.entity.Pubs;

import java.util.List;

@UseCase
public interface ReadPubsUsecase {
    List<ReadPubsWaitingUserListResponseDto> execute();
}
