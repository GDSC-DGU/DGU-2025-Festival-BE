package gdg.festa.application.usecase.notice;

import gdg.festa.core.annotation.UseCase;

@UseCase
public interface DeleteNoticesUsecase {
    void execute(Long noticeId);
}
