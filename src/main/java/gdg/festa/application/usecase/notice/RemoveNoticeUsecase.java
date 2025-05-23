package gdg.festa.application.usecase.notice;

import gdg.festa.core.annotation.UseCase;

@UseCase
public interface RemoveNoticeUsecase {
    void execute(Long noticeId);
}
