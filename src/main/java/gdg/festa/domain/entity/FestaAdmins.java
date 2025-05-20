package gdg.festa.domain.entity;

import gdg.festa.domain.type.ERole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "festa_admins")
public class FestaAdmins {
    @Id
    @Column(name = "festa_admins_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID festaAdminsId;

    @Column(name = "login_id",nullable = false)
    private String loginId;

    @Column(name = "password",nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private ERole role;

    @Builder
    public FestaAdmins(String loginId, String password, ERole role) {
        this.loginId = loginId;
        this.password = password;
        this.role = role;
    }
}