package ru.itis.sharing.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rent_log")
public class Rent {

    @GeneratedValue
    @Id
    private Long id;

    private Date fromDate;

    private Date toDate;

    private Product.Period period;

    private Integer countOfPeriod;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
