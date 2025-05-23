package gdg.festa.application.usecase.notices;

import gdg.festa.core.annotation.UseCase;

@UseCase
public interface DeleteNoticesUsecase {
    void execute(Long noticeId);
}
