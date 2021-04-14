package ua.polkovnik.SpringBootApp.service;

import ua.polkovnik.SpringBootApp.models.CommonUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    boolean addUser(CommonUser user);
}
