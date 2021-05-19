package ru.itis.sharing.dto.Log;

import lombok.Builder;
import lombok.Data;
import ru.itis.sharing.model.Product;

import java.util.Date;

@Data
@Builder
public class LogDto {
    private Long productId;
    private Date from;
    private Date to;
    private Product.Period period;
    private Integer countOfPeriod;
}
