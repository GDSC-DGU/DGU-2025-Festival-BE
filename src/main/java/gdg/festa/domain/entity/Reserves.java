package gdg.festa.domain.entity;

import gdg.festa.domain.type.PubsStatus;
import gdg.festa.domain.type.ReserveStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
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

    @Column(name = "attendance", nullable = false)
    private Long attendance;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "reserve_state",nullable = false)
    @Enumerated(EnumType.STRING)
    private ReserveStatus reserveStatus;

    public void updateStatus() {
        this.reserveStatus = ReserveStatus.CANCEL;
    }


}
