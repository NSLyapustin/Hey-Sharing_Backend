package ru.itis.sharing.service.ProductService;

import javassist.NotFoundException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.sharing.dto.Product.ProductDetailDto;
import ru.itis.sharing.dto.Product.ProductShortDto;
import ru.itis.sharing.model.ApplicationUser;
import ru.itis.sharing.model.Product;
import ru.itis.sharing.repository.ProductRepository;
import ru.itis.sharing.repository.UserRepository;
import ru.itis.sharing.security.UserDetails.ApplicationUserDetails;
import ru.itis.sharing.security.jwt.JwtTokenProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private String image_path;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    private Random random = new Random();

    @Override
    public List<ProductShortDto> getProductsForMainPage() {
        var products = productRepository.findProductsForMainPage();
        for (Product product: products) {
            checkProductStatus(product);
        }
        return products.stream().map(ProductShortDto::from).collect(Collectors.toList());
    }

    @Override
    public List<ProductShortDto> getProductsForMainPageByCategory(Product.Category category) {
        var products = productRepository.findProductsByCategory(category.toString());
        for (Product product: products) {
            checkProductStatus(product);
        }
        return products.stream().map(ProductShortDto::from).collect(Collectors.toList());
    }

    @Override
    public List<ProductShortDto> getProductsByName(String name) {
        var products = productRepository.findProductsByName(name);
        for (Product product: products) {
            checkProductStatus(product);
        }
        return products.stream().map(ProductShortDto::from).collect(Collectors.toList());
    }

    @Override
    public ProductDetailDto getProductById(Long id) throws NotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("product not found"));
        var productDto = ProductDetailDto.from(product);
        product.setCountOfViews(product.getCountOfViews() + 1);
        productRepository.save(product);
        return productDto;
    }

    @Override
    public void saveProduct(ProductDetailDto productDto, String username) {
        ApplicationUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        Product product = Product.builder()
                .name(productDto.getName())
                .image(productDto.getImage())
                .category(productDto.getCategory())
                .address(productDto.getAddress())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .period(productDto.getPeriod())
                .status(Product.Status.AWAITING_CONFIRMATION)
                .userId(user)
                .countOfViews(0)
                .build();
        productRepository.save(product);
    }

    private void checkProductStatus(Product product) {
        Date lastDate = product.getRents().stream().map(rent -> rent.getToDate()).max(Date::compareTo).orElse(new Date());
        if (lastDate.compareTo(new Date()) < 1) {
            product.setStatus(Product.Status.AT_THE_RECEPTION_POINT);
            productRepository.save(product);
        }
    }

    @Override
    public String saveImage(MultipartFile image) throws IOException {
        String name = UUID.randomUUID().toString() + image.getOriginalFilename();
        File convertFile = new File("src/main/resources/photos/" + name);
        Files.createFile(convertFile.toPath());
        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(image.getBytes());
        fileOutputStream.close();
        return name;
    }

    @Override
    public void confirmProduct(Long productId) throws NotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("product not found"));
        product.setStatus(Product.Status.AT_THE_RECEPTION_POINT);
        productRepository.save(product);
    }
}
