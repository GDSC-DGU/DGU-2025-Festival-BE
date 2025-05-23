package gdg.festa.domain.repository;

import gdg.festa.domain.entity.NoticeImages;
import gdg.festa.domain.entity.Notices;

import java.util.List;

public interface NoticeImagesRepository {

    void save(NoticeImages noticeImages);
    void saveAll(List<NoticeImages> noticeImages);
    List<NoticeImages> findByNoticeAndDeletedAtIsNull(Notices notices);
}
