package gdg.festa.domain.entity;

import gdg.festa.domain.type.TagStatus;
import gdg.festa.presentation.request.losts.LostsRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "losts")
public class Losts {
    @Id
    @Column(name = "losts_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lostsId;

    @Column(name = "losts_title",nullable = false)
    private String title;

    @Column(name = "losts_color",nullable = false)
    private String color;

    @Column(name = "losts_brand")
    private String brand;

    @Column(name = "losts_location")
    private String location;

    @Column(name = "losts_note")
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(name = "losts_tag", nullable = false)
    private TagStatus tag;

    @Column(name = "losts_category")
    private String category;

    public void setLosts(LostsRequestDto lostsRequestDto){
        if (lostsRequestDto.title() != null) this.title = lostsRequestDto.title();
        if (lostsRequestDto.color() != null) this.color = lostsRequestDto.color();
        if (lostsRequestDto.brand() != null) this.brand = lostsRequestDto.brand();
        if (lostsRequestDto.location() != null) this.location = lostsRequestDto.location();
        if (lostsRequestDto.note() != null) this.note = lostsRequestDto.note();
        if (lostsRequestDto.tag() != null) this.tag = lostsRequestDto.tag();
        if (lostsRequestDto.category() != null) this.category = lostsRequestDto.category();
    }
}
