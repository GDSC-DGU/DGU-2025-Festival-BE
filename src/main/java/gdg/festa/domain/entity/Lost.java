package gdg.festa.domain.entity;

import gdg.festa.domain.type.TagStatus;
import gdg.festa.presentation.request.losts.LostsRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@Builder(builderMethodName = "LostsBuilder")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "losts")
public class Lost extends BaseEntity {

    @Id
    @Column(name = "lost_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lostId;

    @Column(name = "lost_title",nullable = false)
    private String title;

    @Column(name = "lost_color",nullable = false)
    private String color;

    @Column(name = "lost_brand")
    private String brand;

    @Column(name = "lost_location")
    private String location;

    @Column(name = "lost_note")
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(name = "lost_tag", nullable = false)
    private TagStatus tag;

    @Column(name = "lost_category")
    private String category;

    public void setLost(LostsRequestDto lostsRequestDto){
        if (lostsRequestDto.title() != null) this.title = lostsRequestDto.title();
        if (lostsRequestDto.color() != null) this.color = lostsRequestDto.color();
        if (lostsRequestDto.brand() != null) this.brand = lostsRequestDto.brand();
        if (lostsRequestDto.location() != null) this.location = lostsRequestDto.location();
        if (lostsRequestDto.note() != null) this.note = lostsRequestDto.note();
        if (lostsRequestDto.tag() != null) this.tag = lostsRequestDto.tag();
        if (lostsRequestDto.category() != null) this.category = lostsRequestDto.category();
    }
}
