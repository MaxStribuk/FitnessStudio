package by.itacademy.service.impl;

import by.itacademy.core.dto.request.UserLoginDto;
import by.itacademy.core.dto.request.UserRegistrarionDto;
import by.itacademy.core.dto.request.UserVerificationDto;
import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.repository.api.IUserRepository;
import by.itacademy.repository.entity.UserEntity;
import by.itacademy.service.api.ISenderService;
import by.itacademy.service.api.IUserService;
import org.springframework.core.convert.converter.Converter;

public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final ISenderService senderService;
    private final Converter<UserRegistrarionDto, UserEntity> userRegistrarionDtoEntityConverter;

    public UserService(IUserRepository userRepository,
                       Converter<UserRegistrarionDto, UserEntity> userRegistrarionDtoEntityConverter,
                       ISenderService senderService) {
        this.userRepository = userRepository;
        this.userRegistrarionDtoEntityConverter = userRegistrarionDtoEntityConverter;
        this.senderService = senderService;
    }

    @Override
    public void add(UserRegistrarionDto user) {
        UserEntity userEntity = userRegistrarionDtoEntityConverter.convert(user);
        userEntity = userRepository.save(userEntity);
        senderService.addVerificationMail(userEntity);
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