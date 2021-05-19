package ru.itis.sharing.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
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
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private ApplicationUser userId;

    @ManyToMany
    @JoinTable(name = "favorites",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private List<ApplicationUser> likedUsersList;

    @OneToMany(mappedBy = "product")
    private List<Rent> rents;

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
        CLOTHES;

        public String toString() {
            switch (this) {
                case VEHICLE:
                    return "VEHICLE";
                case APPLIANCES:
                    return "APPLIANCES";
                case ELECTRONICS:
                    return "ELECTRONICS";
                case FURNITURE:
                    return "FURNITURE";
                case HOBBIES_AND_LEISURE:
                    return "HOBBIES_AND_LEISURE";
                case CLOTHES:
                    return "CLOTHES";
                default:
                    return "ALL";
            }
        }
    }

    public enum Period {
        DAY,
        WEEK,
        MONTH
    }
}
