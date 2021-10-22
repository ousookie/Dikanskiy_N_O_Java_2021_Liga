package ru.dikanskiy.security.auth;

import java.util.List;
import java.util.Optional;

public interface ApplicationUserDAO {

    List<ApplicationUser> getUsers();

    Optional<ApplicationUser> getUserByUsername(String username);

}
