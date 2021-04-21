package ru.itis.sharing.service.UserService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.itis.sharing.dto.Auth.UserAuthDto;
import ru.itis.sharing.model.ApplicationUser;

public interface UserService {
    public UserAuthDto addUser(UserAuthDto userDto);
    public ApplicationUser loadUserByUsername(String username) throws UsernameNotFoundException;
    public UserAuthDto login(UserAuthDto userDto);
    public UserAuthDto banByUsername(String username);
    public UserAuthDto unbanByUsername(String username);
}
