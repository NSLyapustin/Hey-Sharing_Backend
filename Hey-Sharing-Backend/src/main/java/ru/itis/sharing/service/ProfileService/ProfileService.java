package ru.itis.sharing.service.ProfileService;

import ru.itis.sharing.dto.Product.ProductShortDto;

import java.util.List;

public interface ProfileService {
    Integer getUserIncome(String username);
    List<ProductShortDto> getUserProducts(String username);
}
