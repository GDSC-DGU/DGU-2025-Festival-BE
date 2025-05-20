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
@Table(name = "booths")
public class Booths {
    @Id
    @Column(name = "booths_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boothsId;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "like_cnt",nullable = false)
    private Long likeCnt;
}
