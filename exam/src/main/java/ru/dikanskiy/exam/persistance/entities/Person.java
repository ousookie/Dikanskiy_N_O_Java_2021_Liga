package ru.dikanskiy.exam.persistance.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.dikanskiy.exam.persistance.entities.base.Identifiable;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
public class Person extends Identifiable implements UserDetails {

    @Column(name = "username", columnDefinition = "varchar(255)")
    private String username;

    @Column(name = "password", columnDefinition = "varchar(255)")
    private String password;

    @Column(name = "created_at", columnDefinition = "timestamp")
    private Date createdAt;

    @Column(name = "granted_authority", columnDefinition = "varchar(255)")
    private String authority;

    @Column(name = "is_account_non_expired", columnDefinition = "boolean")
    private boolean isAccountNonExpired;

    @Column(name = "is_account_non_locked", columnDefinition = "boolean")
    private boolean isAccountNonLocked;

    @Column(name = "is_credentials_non_expired", columnDefinition = "boolean")
    private boolean isCredentialsNonExpired;

    @Column(name = "is_enabled", columnDefinition = "boolean")
    private boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(authority));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
