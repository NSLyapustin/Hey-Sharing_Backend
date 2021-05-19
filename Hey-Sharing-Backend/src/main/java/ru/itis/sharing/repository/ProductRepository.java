package ru.itis.sharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.sharing.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM product LIMIT 30")
    List<Product> findProductsForMainPage();

    @Query(nativeQuery = true, value = "SELECT * FROM product WHERE category = ?1 LIMIT 30")
    List<Product> findProductsByCategory(String category);
}
