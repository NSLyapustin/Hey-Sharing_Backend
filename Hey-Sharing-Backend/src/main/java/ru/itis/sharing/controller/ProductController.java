package ru.itis.sharing.controller;

import javassist.NotFoundException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.sharing.dto.Product.ProductDetailDto;
import ru.itis.sharing.dto.Product.ProductShortDto;
import ru.itis.sharing.model.Product;
import ru.itis.sharing.repository.ProductRepository;
import ru.itis.sharing.security.UserDetails.ApplicationUserDetails;
import ru.itis.sharing.service.ProductService.ProductService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product/save")
    public ResponseEntity saveProduct(@RequestBody ProductDetailDto productDto, @AuthenticationPrincipal ApplicationUserDetails userDetails) {
        productService.saveProduct(productDto, userDetails.getUsername());
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @GetMapping("/product/getAll")
    public ResponseEntity<List<ProductShortDto>> getAll() {
        return new ResponseEntity(productService.getProductsForMainPage(), HttpStatus.OK);
    }

    @GetMapping("/product/getAll/{category}")
    public ResponseEntity<List<ProductShortDto>> getAllInCategory(@PathVariable("category") Product.Category category) {
        if (category == Product.Category.ALL) {
            return new ResponseEntity(productService.getProductsForMainPage(), HttpStatus.OK);
        }
        return new ResponseEntity(productService.getProductsForMainPageByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductShortDto> getProduct(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity(productService.getProductById(id), HttpStatus.OK);
    }

    @PostMapping("/product/saveImage")
    public ResponseEntity<?> saveImage(@RequestParam("image_file") MultipartFile image) throws IOException {
        String filePath = productService.saveImage(image);
        Map<String, String> response = new HashMap<>();
        response.put("filePath", filePath);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/product/by/{name}")
    public ResponseEntity<List<ProductShortDto>> getByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(productService.getProductsByName(name), HttpStatus.OK);
    }

    @GetMapping("/image/{photoName}")
    public ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable(value="photoName") String photoName) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        InputStream in = new FileInputStream(new File("src/main/resources/photos/" + photoName));
        byte[] media = in.readAllBytes();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        return new ResponseEntity<>(media, headers, HttpStatus.OK);
    }

    @PutMapping("/product/confirm/{productId}")
    public ResponseEntity<Boolean> confirmProduct(@PathVariable(value="productId") Long productId) throws NotFoundException {
        productService.confirmProduct(productId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
