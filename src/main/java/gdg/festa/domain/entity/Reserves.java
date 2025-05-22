package gdg.festa.domain.entity;

import gdg.festa.domain.type.ReserveStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "reserves")
public class Reserves extends BaseEntity{
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

    @Column(name = "browser_token")
    private String browserToken;

    @Builder(builderMethodName = "reservesBuilder")
    public Reserves(String phoneNumber, String browserToken) {
        super(LocalDateTime.now(), LocalDateTime.now(), null);
        this.phoneNumber = phoneNumber;
        this.reserveStatus = ReserveStatus.ENABLED;
        this.browserToken = browserToken;
    }

    public void updateStatus() {
        this.reserveStatus = ReserveStatus.CANCELED;
    }

    public void updateStatus(ReserveStatus reserveStatus) {
        this.reserveStatus = reserveStatus;
    }
    public void updateReserve(Long attendance, String name, Pubs pubs) {
        this.attendance = attendance;
        this.name = name;
        this.pubs = pubs;
        this.reserveStatus = ReserveStatus.WAITING;
    }


}
