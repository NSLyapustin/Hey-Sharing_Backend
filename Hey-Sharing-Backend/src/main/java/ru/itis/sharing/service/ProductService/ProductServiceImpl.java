package ru.itis.sharing.service.ProductService;

import org.springframework.stereotype.Service;
import ru.itis.sharing.dto.Product.ProductDetailDto;
import ru.itis.sharing.dto.Product.ProductShortDto;
import ru.itis.sharing.model.Product;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public ProductShortDto[] getProductsForMainPage() {
        return new ProductShortDto[0];
    }

    @Override
    public ProductShortDto[] getProductsFromMainPageByCategory(Product.Category category) {
        return new ProductShortDto[0];
    }

    @Override
    public ProductDetailDto getProductById() {
        return null;
    }

    @Override
    public void saveProduct(Product product) {

    }
}
