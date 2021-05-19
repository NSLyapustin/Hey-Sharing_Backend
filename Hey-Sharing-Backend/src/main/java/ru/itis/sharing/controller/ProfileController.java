package ru.itis.sharing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.sharing.dto.Product.ProductShortDto;
import ru.itis.sharing.security.UserDetails.ApplicationUserDetails;
import ru.itis.sharing.service.ProfileService.ProfileService;

import java.util.List;

@RestController
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/profile/products")
    public ResponseEntity<List<ProductShortDto>> getUserProducts(@AuthenticationPrincipal ApplicationUserDetails userDetails) {
        List<ProductShortDto> products = profileService.getUserProducts(userDetails.getUsername());
        return new ResponseEntity(products, HttpStatus.OK);
    }

    @GetMapping("/profile/income")
    public ResponseEntity<Integer> getUserIncome(@AuthenticationPrincipal ApplicationUserDetails userDetails) {
        Integer income = profileService.getUserIncome(userDetails.getUsername());
        return new ResponseEntity(income, HttpStatus.OK);
    }
}
