package pl.kl.companycarfleetmanagementsystem.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@Data
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Username cannot be blank")
    private String username;
    @NotNull(message = "Password year cannot be blank")
    private String password;
    @NotNull(message = "Authorities cannot be blank")
    private String authorities;

    public ApplicationUser(String username, String password, List<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.setAuthorities(authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(authorities.split(";"))
                .map(authority -> (GrantedAuthority) () -> authority)
                .collect(Collectors.toList());
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(";"));
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
