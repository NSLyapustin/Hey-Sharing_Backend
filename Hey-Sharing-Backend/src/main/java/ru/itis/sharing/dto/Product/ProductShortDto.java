package ru.itis.sharing.dto.Product;

import lombok.Builder;
import lombok.Data;
import ru.itis.sharing.dto.Auth.UserAuthDto;
import ru.itis.sharing.model.ApplicationUser;
import ru.itis.sharing.model.Product;

@Builder
@Data
public class ProductShortDto {
    Long id;
    String image;
    String name;
    Integer price;
    String status;
    Product.Period period;
    Product.Category category;

    public static ProductShortDto from(Product product) {
        return ProductShortDto.builder()
                .id(product.getId())
                .image(product.getImage())
                .name(product.getName())
                .status(product.getStatus().toString())
                .price(product.getPrice())
                .period(product.getPeriod())
                .category(product.getCategory())
                .build();
    }
}
