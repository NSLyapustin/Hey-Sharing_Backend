package ru.itis.sharing.dto.Product;

import lombok.Builder;
import lombok.Data;
import ru.itis.sharing.model.Product;

@Builder
@Data
public class ProductDetailDto {
    Long id;
    String name;
    String image;
    Integer price;
    Product.Period period;
    String description;
    Integer countOfViews;
    Product.Category category;
    Product.Status status;
    String address;

    public static ProductDetailDto from(Product product) {
        return ProductDetailDto.builder()
                .id(product.getId())
                .name(product.getName())
                .image(product.getImage())
                .price(product.getPrice())
                .period(product.getPeriod())
                .description(product.getDescription())
                .countOfViews(product.getCountOfViews())
                .category(product.getCategory())
                .status(product.getStatus())
                .address(product.getAddress())
                .build();
    }
}
