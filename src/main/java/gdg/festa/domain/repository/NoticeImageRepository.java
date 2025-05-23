package gdg.festa.domain.repository;

import gdg.festa.domain.entity.NoticeImage;
import gdg.festa.domain.entity.Notice;

import java.util.List;

public interface NoticeImageRepository {

    void save(NoticeImage noticeImage);
    void saveAll(List<NoticeImage> noticeImage);
    List<NoticeImage> findByNoticeAndDeletedAtIsNull(Notice notice);
}
