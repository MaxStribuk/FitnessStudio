package by.itacademy.core.dto.response;

import by.itacademy.core.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

public class CurrentUserDto extends User {

    private UUID uuid;

    private String fio;

    private UserRole role;

    public CurrentUserDto(String username, String password,
                          Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public CurrentUserDto(String username, String password,
                          boolean enabled, boolean accountNonExpired,
                          boolean credentialsNonExpired, boolean accountNonLocked,
                          Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);
    }

    public CurrentUserDto(UserDetails user){
        super(user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isAccountNonLocked(),
                user.getAuthorities());
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CurrentUserDto that = (CurrentUserDto) o;
        return Objects.equals(uuid, that.uuid)
                && Objects.equals(fio, that.fio)
                && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), uuid, fio, role);
    }

    @Override
    public String toString() {
        return "CurrentUserDto{" +
                "uuid=" + uuid +
                ", fio='" + fio + '\'' +
                ", role=" + role +
                '}';
    }
}