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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder(builderMethodName = "LostImagesBuilder")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "lost_images")
public class LostImages extends BaseEntity {
    @Id
    @Column(name = "lost_images_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lostImagesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "losts_id")
    private Losts losts;

    @Column(name = "lost_images_image_url")
    private String imageUrl;
}
