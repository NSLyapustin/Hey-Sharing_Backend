package ru.itis.sharing.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = 'product')
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Integer price;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Period period;

    @Column
    private String image;

    @Column
    private Integer countOfViews;

    @Column
    private String description;

    @Column
    private Category category;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column
    private String address;

    @Column
    @ManyToOne
    @JoinColumn(name = "user_id")
    private ApplicationUser userId;

    public enum Status {
        AWAITING_CONFIRMATION,
        AT_THE_RECEPTION_POINT,
        AT_THE_TENANT
    }

    public enum Category {
        ALL,
        VEHICLE,
        APPLIANCES,
        ELECTRONICS,
        FURNITURE,
        HOBBIES_AND_LEISURE,
        CLOTHES
    }

    public enum Period {
        DAY,
        WEEK,
        MONTH
    }
}
