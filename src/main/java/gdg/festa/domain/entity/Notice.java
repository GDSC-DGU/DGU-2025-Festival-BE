package gdg.festa.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notices")
public class Notice extends BaseEntity {

    @Id
    @Column(name = "notice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;

    @Column(name = "notice_title", nullable = false)
    private String title;

    @Column(name = "notice_note", length = 1024)
    private String note;

    @Builder(builderMethodName = "noticeBuilder")
    public Notice(String title, String note) {
        super(LocalDateTime.now(), LocalDateTime.now(), null);
        this.title = title;
        this.note = note;
    }

}
