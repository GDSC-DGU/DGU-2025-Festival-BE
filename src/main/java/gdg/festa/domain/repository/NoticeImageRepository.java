package gdg.festa.domain.repository;

import gdg.festa.domain.entity.NoticeImage;

import java.util.List;

public interface NoticeImageRepository {

    void save(NoticeImage noticeImage);
    void saveAll(List<NoticeImage> noticeImages);

}
