package gdg.festa.domain.repository;

import gdg.festa.domain.entity.Notices;

import java.util.Optional;

public interface NoticesRepository {
    void save(Notices notices);
    Notices findById(Long noticeId);
}
