package ru.itis.sharing.service.ProductService;

import javassist.NotFoundException;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.sharing.dto.Product.ProductDetailDto;
import ru.itis.sharing.dto.Product.ProductShortDto;
import ru.itis.sharing.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductShortDto> getProductsForMainPage();
    List<ProductShortDto> getProductsFromMainPageByCategory(Product.Category category);
    ProductDetailDto getProductById(Long id) throws NotFoundException;
    void saveProduct(ProductDetailDto productDto, String username);
//    String saveImage(MultipartFile image) throws Exception;
}
