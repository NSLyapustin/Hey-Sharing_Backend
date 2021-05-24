package ru.itis.sharing.service.ProfileService;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.sharing.dto.Product.ProductShortDto;
import ru.itis.sharing.model.ApplicationUser;
import ru.itis.sharing.model.Product;
import ru.itis.sharing.model.Rent;
import ru.itis.sharing.repository.ProductRepository;
import ru.itis.sharing.repository.UserRepository;
import ru.itis.sharing.security.UserDetails.ApplicationUserDetails;
import ru.itis.sharing.security.jwt.JwtTokenProvider;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Integer getUserIncome(String username) {
        ApplicationUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        var products = user.getProducts();
        Integer income = 0;
        for (Product product: products) {
            for (Rent rent: product.getRents()) {
                if (rent.getToDate().compareTo((new Date())) == 1) {
                    income = income + product.getPrice() * rent.getCountOfPeriod();
                };
            }
        }
        return income;
//        return 12345;
    }

    @Override
    public List<ProductShortDto> getUserProducts(String username) {
        ApplicationUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        var products = user.getProducts();
        for (Product product: products) {
            checkProductStatus(product);
        }
        return products.stream().map(ProductShortDto::from).collect(Collectors.toList());
    }

    private void checkProductStatus(Product product) {
        Date lastDate = product.getRents().stream().map(rent -> rent.getToDate()).max(Date::compareTo).orElse(new Date());
        if (lastDate.compareTo(new Date()) < 1) {
            if (product.getStatus().equals(Product.Status.AT_THE_TENANT)) {
                product.setStatus(Product.Status.AT_THE_RECEPTION_POINT);
                productRepository.save(product);
            }
        }
    }
}
