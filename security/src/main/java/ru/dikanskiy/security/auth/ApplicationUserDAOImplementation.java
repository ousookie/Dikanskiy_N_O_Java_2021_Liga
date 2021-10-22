package ru.dikanskiy.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("in_memory_repository")
public class ApplicationUserDAOImplementation implements ApplicationUserDAO {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserDAOImplementation(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<ApplicationUser> getUsers() {
        return List.of(
                new ApplicationUser(
                        "nick",
                        passwordEncoder.encode("123"),
                        List.of(new SimpleGrantedAuthority("ADMIN"),
                                new SimpleGrantedAuthority("USER")),
                        true,
                        true,
                        true,
                        true),
                new ApplicationUser("anna", passwordEncoder.encode("456"),
                        List.of(new SimpleGrantedAuthority("USER")),
                        true,
                        true,
                        true,
                        true),
                new ApplicationUser("sam", passwordEncoder.encode("789"),
                        List.of(new SimpleGrantedAuthority("USER")),
                        true,
                        true,
                        true,
                        true));
    }

    @Override
    public Optional<ApplicationUser> getUserByUsername(String username) {
        return getUsers()
                .stream()
                .filter(applicationUser -> applicationUser.getUsername().equals(username))
                .findFirst();
    }

}
