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
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "losts")
public class Losts {
    @Id
    @Column(name = "losts_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lostsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categories_id")
    private Categories categories;

    @Column(name = "tilte")
    private String title;

    @Column(name = "color")
    private String color;

    @Column(name = "brand")
    private String brand;

    @Column(name = "location")
    private String location;

    @Column(name = "note")
    private String note;

    @Column(name = "tag")
    private String tag;

    public void setCategories(Categories categories){
        this.categories=categories;
    }
}
