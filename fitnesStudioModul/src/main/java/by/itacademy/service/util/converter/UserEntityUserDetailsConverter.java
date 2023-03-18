package by.itacademy.service.util.converter;

import by.itacademy.core.dto.response.CurrentUserDto;
import by.itacademy.core.enums.UserRole;
import by.itacademy.core.enums.UserStatus;
import by.itacademy.repository.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class UserEntityUserDetailsConverter implements Converter<UserEntity, UserDetails> {

    @Override
    public UserDetails convert(UserEntity user) {
        UserDetails userDetails = User.builder()
                .username(user.getMail())
                .password(user.getPassword())
                .disabled(user.getStatus()
                        .getStatus()
                        .equals(UserStatus.ACTIVATED))
                .roles(getRoles(user)
                        .toArray(String[]::new))
                .build();
        CurrentUserDto currentUserDto = new CurrentUserDto(userDetails);
        currentUserDto.setFio(user.getFio());
        currentUserDto.setUuid(user.getUuid());
        currentUserDto.setRole(user.getRole().getRole());
        return currentUserDto;
    }

    private List<String> getRoles(UserEntity user) {
        if (user.getRole().getRole().equals(UserRole.USER)) {
            return List.of(UserRole.USER.name());
        } else {
            return List.of(
                    UserRole.USER.name(),
                    UserRole.ADMIN.name());
        }
    }
}