package ru.itis.sharing.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
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
}
