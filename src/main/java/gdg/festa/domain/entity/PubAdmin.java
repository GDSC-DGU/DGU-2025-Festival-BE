package gdg.festa.domain.entity;

import gdg.festa.domain.type.ERole;
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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pub_admins")
public class PubAdmin {

    @Id
    @Column(name = "pub_admin_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID pubAdminId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pub_id")
    private Pub pub;

    @Column(name = "pub_admin_login_id",nullable = false)
    private String loginId;

    @Column(name = "pub_admin_password",nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "pub_admin_role",nullable = false)
    private ERole role;

    @Builder
    public PubAdmin(String loginId, String password, ERole role) {
        this.loginId = loginId;
        this.password = password;
        this.role = role;
    }
}
