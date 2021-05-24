package ru.itis.sharing.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.sharing.security.jwt.JwtTokenProvider;

@RestController
public class TokenController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/validateToken")
    public ResponseEntity<Boolean> checkToken(@RequestHeader(name = "Authorization") String token) {
        if (jwtTokenProvider.validate(token)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity(false, HttpStatus.FORBIDDEN);
    }
}
