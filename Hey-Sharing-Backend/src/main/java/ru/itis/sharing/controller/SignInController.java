package ru.itis.sharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.sharing.dto.Auth.TokenDto;
import ru.itis.sharing.dto.Auth.UserAuthDto;
import ru.itis.sharing.model.ApplicationUser;
import ru.itis.sharing.security.jwt.JwtTokenProvider;
import ru.itis.sharing.service.UserService.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SignInController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody UserAuthDto userDto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getUsername(),
                        userDto.getPassword()
                ));

        ApplicationUser user = userService.loadUserByUsername(userDto.getUsername());
        String token = jwtTokenProvider.createToken(user.getUsername(), user.getRole().toString());
        TokenDto tokenDto = new TokenDto(token);
        Map<Object, Object> response = new HashMap<>();
        if (!user.getIsActive()) {
            response.put("err", "User is banned");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        response.put("token", tokenDto);
        return ResponseEntity.ok(response);
    }
}
