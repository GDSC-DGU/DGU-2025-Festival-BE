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
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pubs_admin")
public class PubsAdmin {
    @Id
    @Column(name = "pubs_admin_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID pubsAdminId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pubs_id")
    private Pubs pubs;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "login_id",nullable = false)
    private String loginId;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "pubs_name",nullable = false)
    private String pubsName;

    @Column(name = "role",nullable = false)
    private String role;
}
