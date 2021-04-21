package ru.itis.sharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.sharing.dto.Auth.UserBanDto;
import ru.itis.sharing.model.ApplicationUser;
import ru.itis.sharing.repository.UserRepository;
import ru.itis.sharing.service.UserService.UserService;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/getAll")
    ResponseEntity<List<ApplicationUser>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/ban")
    ResponseEntity<ApplicationUser> banUser(@RequestBody UserBanDto userBanDto) {
        return new ResponseEntity(userService.banByUsername(userBanDto.getUsername()), HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/unban")
    ResponseEntity<ApplicationUser> unbanUser(@RequestBody UserBanDto userBanDto) {
        return new ResponseEntity(userService.unbanByUsername(userBanDto.getUsername()), HttpStatus.OK);
    }
}
