package gdg.festa.application.usecase.notices;

import gdg.festa.core.annotation.UseCase;

@UseCase
public interface RemoveNoticeUsecase {
    void execute(Long noticeId);
}
