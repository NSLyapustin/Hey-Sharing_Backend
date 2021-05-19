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
@Table(name = "account")
public class ApplicationUser {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column
    private Boolean isActive;

    public enum Role {
        DEFAULT,
        MODER,
        ADMIN
    }

    @OneToMany(mappedBy = "userId")
    private List<Product> products;

    @ManyToMany(mappedBy = "likedUsersList")
    private List<Product> favoriteProducts;
}
