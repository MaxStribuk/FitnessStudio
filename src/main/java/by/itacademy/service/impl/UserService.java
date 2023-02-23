package by.itacademy.service.impl;

import by.itacademy.core.dto.request.UserLoginDto;
import by.itacademy.core.dto.request.UserRegistrarionDto;
import by.itacademy.core.dto.request.UserVerificationDto;
import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.dao.api.IUserRepository;
import by.itacademy.dao.entity.UserEntity;
import by.itacademy.service.api.IUserService;
import org.springframework.core.convert.converter.Converter;

public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final Converter<UserRegistrarionDto, UserEntity> userRegistrarionDtoEntityConverter;

    public UserService(IUserRepository userRepository,
                       Converter<UserRegistrarionDto, UserEntity> userRegistrarionDtoEntityConverter) {
        this.userRepository = userRepository;
        this.userRegistrarionDtoEntityConverter = userRegistrarionDtoEntityConverter;
    }

    @Override
    public void add(UserRegistrarionDto user) {
        UserEntity userEntity = userRegistrarionDtoEntityConverter.convert(user);
        userRepository.save(userEntity);
    }

    @Override
    public void verification(UserVerificationDto user) {

    }

    @Override
    public void login(UserLoginDto user) {

    }

    @Override
    public PageUserDto get() {
        return null;
    }
}