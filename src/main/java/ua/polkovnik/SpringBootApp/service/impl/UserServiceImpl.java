package ua.polkovnik.SpringBootApp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.polkovnik.SpringBootApp.models.CommonUser;
import ua.polkovnik.SpringBootApp.service.UserRepository;
import ua.polkovnik.SpringBootApp.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String ROLE_READER = "READER";

//    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public Optional<CommonUser> loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean addUser(CommonUser user) {
        if (userRepository.findByUsername(user.getUsername()).isEmpty()) {
            CommonUser userToSave = new CommonUser();
            userToSave.setUsername(user.getUsername());
            userToSave.setPassword(user.getPassword());
            userRepository.save(userToSave);
            return true;
        } else {
            return false;
        }
    }

    private UserDetails buildUserDetails(CommonUser user) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(ROLE_READER)
                .build();
    }
}
