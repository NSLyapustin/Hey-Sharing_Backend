package ru.itis.sharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.sharing.model.Rent;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findAllByProductId(Long productId);
}
