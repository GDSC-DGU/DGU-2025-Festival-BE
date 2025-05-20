package gdg.festa.domain.entity;

import gdg.festa.domain.type.PubsStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "pub_state",nullable = false)
    @Enumerated(EnumType.STRING)
    private PubsStatus pubsStatus;

    public void updateState(PubsStatus pubsStatus) {
        this.pubsStatus = pubsStatus;
    }
}
