package gdg.festa.core.security.info;



import gdg.festa.domain.entity.FestaAdmins;
import gdg.festa.domain.entity.PubsAdmin;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import gdg.festa.domain.entity.User;
import gdg.festa.domain.type.ERole;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Builder
@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {
    @Getter
    private final UUID uuid;
    @Getter
    private final ERole userRole;
    private final Collection<? extends GrantedAuthority> authorities;


    public static UserPrincipal createPub(PubsAdmin pubsAdmin) {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + pubsAdmin.getRole()));
        return UserPrincipal.builder()
                .uuid(pubsAdmin.getPubsAdminId())
                .userRole(pubsAdmin.getRole())
                .authorities(authorities)
                .build();
    }

    public static UserPrincipal createFesta(FestaAdmins festaAdmins) {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + festaAdmins.getRole()));
        return UserPrincipal.builder()
                .uuid(festaAdmins.getFestaAdminsId())
                .userRole(festaAdmins.getRole())
                .authorities(authorities)
                .build();
    }


    @Override
    public String getUsername() {
        return uuid.toString();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
