package ru.itis.sharing.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.sharing.dto.Auth.UserAuthDto;
import ru.itis.sharing.model.ApplicationUser;
import ru.itis.sharing.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserAuthDto addUser(UserAuthDto userDto) {
        return UserAuthDto.from(userRepository.save(ApplicationUser
                .builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(ApplicationUser.Role.DEFAULT)
                .isActive(true)
                .build()));
    }

    @Override
    public ApplicationUser loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return user;
    }

    @Override
    public UserAuthDto login(UserAuthDto userDto) {
        ApplicationUser applicationUser = userRepository.findByUsername(userDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));

        if (passwordEncoder.matches(userDto.getPassword(), applicationUser.getPassword())) {
            return UserAuthDto.from(applicationUser);
        }

        return null;
    }

    @Override
    public UserAuthDto banByUsername(String username) {
        ApplicationUser user = loadUserByUsername(username);
        if (user.getRole().equals(ApplicationUser.Role.ADMIN)) {
            return UserAuthDto.from(user);
        }
        user.setIsActive(false);
        userRepository.save(user);
        return UserAuthDto.from(user);
    }

    @Override
    public UserAuthDto unbanByUsername(String username) {
        ApplicationUser user = loadUserByUsername(username);
        user.setIsActive(true);
        userRepository.save(user);
        return UserAuthDto.from(user);
    }
}
