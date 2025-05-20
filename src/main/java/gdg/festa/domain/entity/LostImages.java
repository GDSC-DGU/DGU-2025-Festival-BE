package gdg.festa.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "lost_images")
public class LostImages {
    @Id
    @Column(name = "lostImages_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lostImagesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "losts_id")
    private Losts losts;

    @Column(name = "image_url")
    private String imageUrl;
}
