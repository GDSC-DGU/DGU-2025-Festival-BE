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
public class Reserve extends BaseEntity {

    @Id
    @Column(name = "reserve_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID reserveId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pub_id")
    private Pub pub;

    @Column(name = "reserve_attendance")
    private Long attendance;

    @Column(name = "reserve_phone_Number", nullable = false)
    private String phoneNumber;

    @Column(name = "reserve_state",nullable = false)
    @Enumerated(EnumType.STRING)
    private ReserveStatus reserveStatus;

    @Column(name = "reserve_name")
    private String name;

    @Column(name = "reserve_browser_token")
    private String browserToken;

    @Builder(builderMethodName = "reservesBuilder")
    public Reserve(String phoneNumber, String browserToken) {
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
    public void updateReserve(Long attendance, String name, Pub pub) {
        this.attendance = attendance;
        this.name = name;
        this.pub = pub;
        this.reserveStatus = ReserveStatus.WAITING;
    }

    public void deleteReserve() {
        this.deletedAt = LocalDateTime.now();
    }


}
