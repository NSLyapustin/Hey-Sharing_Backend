package ru.itis.sharing.security.UserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.itis.sharing.repository.UserRepository;
import ru.itis.sharing.security.UserDetails.ApplicationUserDetails;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new ApplicationUserDetails(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found")));
    }
}
