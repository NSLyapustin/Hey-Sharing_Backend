package ru.itis.sharing.service.ProductService;

import javassist.NotFoundException;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.sharing.dto.Product.ProductDetailDto;
import ru.itis.sharing.dto.Product.ProductShortDto;
import ru.itis.sharing.model.Product;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductShortDto> getProductsForMainPage();
    List<ProductShortDto> getProductsForMainPageByCategory(Product.Category category);
    List<ProductShortDto> getProductsByName(String name);
    ProductDetailDto getProductById(Long id) throws NotFoundException;
    void saveProduct(ProductDetailDto productDto, String username);
    String saveImage(MultipartFile image) throws IOException;
    void confirmProduct(Long productId) throws NotFoundException;
}
