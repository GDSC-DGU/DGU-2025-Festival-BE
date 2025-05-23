package gdg.festa.domain.entity;

import gdg.festa.domain.type.TagStatus;
import gdg.festa.presentation.request.lost.LostRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@Builder(builderMethodName = "LostBuilder")
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

    public void setLost(LostRequestDto lostRequestDto){
        if (lostRequestDto.title() != null) this.title = lostRequestDto.title();
        if (lostRequestDto.color() != null) this.color = lostRequestDto.color();
        if (lostRequestDto.brand() != null) this.brand = lostRequestDto.brand();
        if (lostRequestDto.location() != null) this.location = lostRequestDto.location();
        if (lostRequestDto.note() != null) this.note = lostRequestDto.note();
        if (lostRequestDto.tag() != null) this.tag = lostRequestDto.tag();
        if (lostRequestDto.category() != null) this.category = lostRequestDto.category();
    }
}
