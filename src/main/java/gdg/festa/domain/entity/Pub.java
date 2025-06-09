package gdg.festa.domain.entity;

import gdg.festa.domain.type.PubStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pubs")
public class Pub {

    @Id
    @Column(name = "pub_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pubId;

    @Column(name = "pub_name")
    private String name;

    @Column(name = "pub_location")
    private String location;

    @Column(name = "pub_menus")
    private String menus;

    @Column(name = "pub_picture")
    private String picture;

    @Column(name = "pub_note")
    private String note;

    @Column(name = "pub_wait_people")
    private Long waitPeople;

    @Column(name = "pub_state",nullable = false)
    @Enumerated(EnumType.STRING)
    private PubStatus pubStatus;

    public Pub(Long pubId, String name, String location, String menus, String picture, String note, Long waitPeople, PubStatus pubStatus) {
        this.pubId = pubId;
        this.name = name;
        this.location = location;
        this.menus = menus;
        this.picture = picture;
        this.note = note;
        this.waitPeople = waitPeople;
        this.pubStatus = pubStatus;
    }

    public void updateState(PubStatus pubStatus) {
        this.pubStatus = pubStatus;
    }
    public void updateWaitPeople(Long people) {
        this.waitPeople = people - 1L;
    }
    public void updateWaitPeopleZero() {
        this.waitPeople = 0L;
    }

    public void updateAddWaitPeople(Long people) {
        this.waitPeople = people + 1L;
    }
}
