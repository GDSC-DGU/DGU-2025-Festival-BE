package gdg.festa.domain.entity;

import gdg.festa.domain.type.TagStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "losts")
public class Losts {
    @Id
    @Column(name = "losts_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lostsId;

    @Column(name = "tilte",nullable = true)
    private String title;

    @Column(name = "color",nullable = true)
    private String color;

    @Column(name = "brand")
    private String brand;

    @Column(name = "location")
    private String location;

    @Column(name = "note")
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(name = "tag", nullable = true)
    private TagStatus tag;

    @Column(name = "category")
    private String category;
}
