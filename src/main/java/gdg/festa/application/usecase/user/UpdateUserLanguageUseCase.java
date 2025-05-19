package gdg.festa.application.usecase.user;

import gdg.festa.core.annotation.UseCase;

import java.util.UUID;


@UseCase
public interface UpdateUserLanguageUseCase {
    Boolean execute(String language, UUID userId);
}
