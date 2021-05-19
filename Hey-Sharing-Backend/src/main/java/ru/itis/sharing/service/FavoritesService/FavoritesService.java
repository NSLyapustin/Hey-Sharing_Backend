package ru.itis.sharing.service.FavoritesService;

import javassist.NotFoundException;
import ru.itis.sharing.dto.Product.ProductShortDto;

import java.util.List;

public interface FavoritesService {
    void addToFavorites(Long productId, String username) throws NotFoundException;
    void removeFromFavorites(Long productId, String username) throws NotFoundException;
    List<ProductShortDto> getUserFavorites(String username) throws NotFoundException;
}
