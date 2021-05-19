package ru.itis.sharing.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.itis.sharing.dto.Product.ProductShortDto;
import ru.itis.sharing.model.Product;
import ru.itis.sharing.security.UserDetails.ApplicationUserDetails;
import ru.itis.sharing.service.FavoritesService.FavoritesService;

import java.util.List;

@RestController
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;

    @PostMapping("/product/add_to_favorite/{productId}")
    public ResponseEntity<Boolean> addToFavorite(@AuthenticationPrincipal ApplicationUserDetails userDetails, @PathVariable("productId") Long productId) throws NotFoundException {
        favoritesService.addToFavorites(productId, userDetails.getUsername());
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @PostMapping("/product/remove_from_favorite/{productId}")
    public ResponseEntity<Boolean> removeFromFavorite(@AuthenticationPrincipal ApplicationUserDetails userDetails, @PathVariable("productId") Long productId) throws NotFoundException {
        favoritesService.removeFromFavorites(productId, userDetails.getUsername());
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @GetMapping("/product/favorites")
    public ResponseEntity<List<ProductShortDto>> getUserFavorites(@AuthenticationPrincipal ApplicationUserDetails userDetails) throws NotFoundException {
        return new ResponseEntity(favoritesService.getUserFavorites(userDetails.getUsername()), HttpStatus.OK);
    }
}
