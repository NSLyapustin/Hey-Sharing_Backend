package ru.itis.sharing.controller;

import javassist.NotFoundException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/product/save")
    public ResponseEntity saveProduct(@RequestBody ProductDetailDto productDto, @AuthenticationPrincipal ApplicationUserDetails userDetails) {
        productService.saveProduct(productDto, userDetails.getUsername());
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @GetMapping("/product/getAll")
    public ResponseEntity<List<ProductShortDto>> getAll() {
        return new ResponseEntity(productService.getProductsForMainPage(), HttpStatus.OK);
    }

    @GetMapping("/product/getAll/{category}")
    public ResponseEntity<List<ProductShortDto>> getAllInCategory(@PathVariable("category") Product.Category category) {
        return new ResponseEntity(productService.getProductsFromMainPageByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductShortDto> getProduct(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity(productService.getProductById(id), HttpStatus.OK);
    }

//    @PostMapping("/product/upload_image")
//    public ResponseEntity<String> uploadImage(@RequestParam("imageFile") MultipartFile image) throws Exception {
//        String url = "";
//        productService.saveImage(image);
//
//        return new ResponseEntity(url, HttpStatus.OK);
//    }
}
