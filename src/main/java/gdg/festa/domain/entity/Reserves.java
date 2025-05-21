package gdg.festa.domain.entity;

import gdg.festa.domain.type.PubsStatus;
import gdg.festa.domain.type.ReserveStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reserves")
public class Reserves {
    @Id
    @Column(name = "reserve_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID reserveId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pubs_id")
    private Pubs pubs;

    @Column(name = "attendance")
    private Long attendance;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "reserve_state",nullable = false)
    @Enumerated(EnumType.STRING)
    private ReserveStatus reserveStatus;

    @Column(name = "name")
    private String name;

    @Builder
    public Reserves(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.reserveStatus = ReserveStatus.ENABLED;
    }


    public void updateStatus() {
        this.reserveStatus = ReserveStatus.CANCELED;
    }
    public void updateReserve(Long attendance, String name, Pubs pubs) {
        this.attendance = attendance;
        this.name = name;
        this.pubs = pubs;
        this.reserveStatus = ReserveStatus.WAITING;
    }


}
