package ru.itis.sharing.service.ProductService;

import ru.itis.sharing.dto.Product.ProductDetailDto;
import ru.itis.sharing.dto.Product.ProductShortDto;
import ru.itis.sharing.model.Product;

public interface ProductService {
    ProductShortDto[] getProductsForMainPage();
    ProductShortDto[] getProductsFromMainPageByCategory(Product.Category category);
    ProductDetailDto getProductById();
    void saveProduct(Product product);
}
