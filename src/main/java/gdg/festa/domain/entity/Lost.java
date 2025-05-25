package gdg.festa.domain.entity;

import gdg.festa.domain.type.TagStatus;
import gdg.festa.presentation.request.lost.CreateLostRequestDto;
import gdg.festa.presentation.request.lost.UpdateLostRequestDto;
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

    public void setLost(UpdateLostRequestDto updateLostRequestDto){
        if (updateLostRequestDto.title() != null) this.title = updateLostRequestDto.title();
        if (updateLostRequestDto.color() != null) this.color = updateLostRequestDto.color();
        if (updateLostRequestDto.brand() != null) this.brand = updateLostRequestDto.brand();
        if (updateLostRequestDto.location() != null) this.location = updateLostRequestDto.location();
        if (updateLostRequestDto.note() != null) this.note = updateLostRequestDto.note();
        if (updateLostRequestDto.tag() != null) this.tag = updateLostRequestDto.tag();
        if (updateLostRequestDto.category() != null) this.category = updateLostRequestDto.category();
    }
}
