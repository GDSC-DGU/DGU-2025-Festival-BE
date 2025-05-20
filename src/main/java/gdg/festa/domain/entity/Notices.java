package gdg.festa.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notices")
public class Notices {
    @Id
    @Column(name = "notices_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticesId;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "note", length = 1024)
    private String note;
}
