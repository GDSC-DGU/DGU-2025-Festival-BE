package gdg.festa.domain.entity;


import gdg.festa.domain.type.EProvider;
import gdg.festa.domain.type.ERole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "serial_id", nullable = false, unique = true)
    private String serialId;

    @Column(name = "nickname", length = 20)
    private String nickname;

    @Column(name =  "loginId")
    private String loginId;

    @Column(name = "user_role", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private ERole role;

    @Column(name = "login_provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private EProvider provider;

    @Column(name = "profile_image_url", length = 2048)
    private String profileImageUrl;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "fcm_token")
    private String fcmToken;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "profile")
    private String profile;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "location")
    private String location;

    @Column(name = "language")
    private String language;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "gender")
    private String  gender;


    @Column(name = "skin_type")
    private String skinType;

    @Column(name = "blood_type")
    private String bloodType;


    //--------------------------------------------------


    //--------------------------------------------------



    public void updateLanguage(String language) {
        this.language = language;
    }

}
