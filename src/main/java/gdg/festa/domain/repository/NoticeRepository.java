package gdg.festa.domain.repository;

import gdg.festa.domain.entity.Notice;

public interface NoticeRepository {
    void save(Notice notice);
    Notice findById(Long noticeId);
}
