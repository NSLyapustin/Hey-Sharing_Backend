package ru.itis.sharing.service.RentSerivce;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.sharing.dto.Log.LogDto;
import ru.itis.sharing.model.Product;
import ru.itis.sharing.model.Rent;
import ru.itis.sharing.repository.ProductRepository;
import ru.itis.sharing.repository.RentRepository;

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addNewLog(LogDto logDto) throws NotFoundException {
        Product product = productRepository.findById(logDto.getProductId()).orElseThrow(() -> new NotFoundException("product not found"));
        Rent rent = Rent.builder()
                .product(product)
                .fromDate(logDto.getFrom())
                .toDate(logDto.getTo())
                .period(logDto.getPeriod())
                .countOfPeriod(logDto.getCountOfPeriod())
                .build();
        product.setStatus(Product.Status.AT_THE_TENANT);
        productRepository.save(product);
        rentRepository.save(rent);
    }
}
