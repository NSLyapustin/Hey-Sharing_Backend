package ru.itis.sharing.service.FavoritesService;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.sharing.dto.Product.ProductShortDto;
import ru.itis.sharing.model.ApplicationUser;
import ru.itis.sharing.model.Product;
import ru.itis.sharing.repository.ProductRepository;
import ru.itis.sharing.repository.UserRepository;
import ru.itis.sharing.security.UserDetails.ApplicationUserDetails;
import ru.itis.sharing.security.UserDetails.ApplicationUserDetailsService;
import ru.itis.sharing.security.jwt.JwtTokenProvider;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritesServiceImpl implements FavoritesService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ApplicationUserDetailsService applicationUserDetailsService;

    @Override
    public void addToFavorites(Long productId, String username) throws NotFoundException {
        ApplicationUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("product not found"));
        user.getFavoriteProducts().add(product);
        product.getLikedUsersList().add(user);
        productRepository.save(product);
        userRepository.save(user);
    }

    @Override
    public void removeFromFavorites(Long productId, String username) throws NotFoundException {
        ApplicationUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("product not found"));
        user.getFavoriteProducts().remove(product);
        product.getLikedUsersList().remove(user);
        productRepository.save(product);
        userRepository.save(user);
    }

    @Override
    public List<ProductShortDto> getUserFavorites(String username) throws NotFoundException {
        ApplicationUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        List<Product> favorites = user.getFavoriteProducts();
        for (Product product: favorites) {
            checkProductStatus(product);
        }
        return favorites.stream().map(ProductShortDto::from).collect(Collectors.toList());
    }

    private void checkProductStatus(Product product) {
        Date lastDate = product.getRents().stream().map(rent -> rent.getToDate()).max(Date::compareTo).orElse(new Date());
        if (lastDate.compareTo(new Date()) < 1) {
            product.setStatus(Product.Status.AT_THE_RECEPTION_POINT);
            productRepository.save(product);
        }
    }
}
