package ru.itis.sharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.sharing.dto.Auth.UserAuthDto;
import ru.itis.sharing.service.UserService.UserService;

@RestController
public class SignUpController {

    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<UserAuthDto> signUp(@RequestBody UserAuthDto userDto) {
        return new ResponseEntity(userService.addUser(userDto), HttpStatus.OK);
    }
}
