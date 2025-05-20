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

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pubs")
public class Pubs {
    @Id
    @Column(name = "pubs_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pubsId;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "menus")
    private String menus;

    @Column(name = "picture")
    private String picture;

    @Column(name = "note")
    private String note;

    @Column(name = "wait_people")
    private Long waitPeople;
}
