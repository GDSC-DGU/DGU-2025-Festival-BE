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

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "festa_admins")
public class FestaAdmins {
    @Id
    @Column(name = "festa_admins_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long festaAdminsId;

    @Column(name = "login_id",nullable = false)
    private String loginId;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "role", nullable = false)
    private String role;
}