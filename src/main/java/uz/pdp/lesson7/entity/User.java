package uz.pdp.lesson7.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.lesson7.entity.enums.Permission;
import uz.pdp.lesson7.entity.template.AbstractEntity;

import javax.persistence.*;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "users")
public class User extends AbstractEntity implements UserDetails {
    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Role role;

    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false)
    private boolean isAccountNonExpired = true;

    @Column(nullable = false)
    private boolean isAccountNonLocked = true;

    @Column(nullable = false)
    private boolean isCredentialsNonExpired = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<Permission> permissions = this.role.getPermissions();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        for (Permission permission : permissions) {
//            grantedAuthorities.add(new GrantedAuthority() {
//                @Override
//                public String getAuthority() {
//                    return permission.name();
//                }
//            });
//            grantedAuthorities.add((GrantedAuthority) () -> permission.name());
//            grantedAuthorities.add((GrantedAuthority) permission::name);
//            grantedAuthorities.add(new SimpleGrantedAuthority(permission.name()));
//        }
        for (Permission permission : this.role.getPermissions()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.name()));
        }
        return grantedAuthorities;
    }

    public User(String fullName, String username, String password, Role role, boolean enabled) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }
}
