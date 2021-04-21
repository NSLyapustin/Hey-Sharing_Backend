package ru.itis.sharing.dto.Auth;

import lombok.Builder;
import lombok.Data;
import ru.itis.sharing.model.ApplicationUser;

@Builder
@Data
public class UserAuthDto {
    private String username;
    private String password;
    private ApplicationUser.Role role;
    private boolean isActive;

    public static UserAuthDto from(ApplicationUser applicationUser) {
        return UserAuthDto.builder()
                .username(applicationUser.getUsername())
                .password(applicationUser.getPassword())
                .role(applicationUser.getRole())
                .isActive(applicationUser.getIsActive())
                .build();
    }
}
