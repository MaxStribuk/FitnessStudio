package by.itacademy.service.impl;

import by.itacademy.core.dto.request.UserLoginDto;
import by.itacademy.core.dto.request.UserRegistrationDto;
import by.itacademy.core.dto.request.UserVerificationDto;
import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.core.enums.UserStatus;
import by.itacademy.core.exceptions.AuthorizationException;
import by.itacademy.core.exceptions.DtoNullPointerException;
import by.itacademy.core.exceptions.EntityNotFoundException;
import by.itacademy.core.exceptions.VerificationException;
import by.itacademy.repository.api.IUserRepository;
import by.itacademy.repository.entity.MailEntity;
import by.itacademy.repository.entity.UserEntity;
import by.itacademy.repository.entity.UserStatusEntity;
import by.itacademy.service.api.ISenderService;
import by.itacademy.service.api.IUserService;
import org.springframework.core.convert.ConversionService;

import java.util.Optional;

public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final ISenderService senderService;
    private final ConversionService conversionService;

    public UserService(IUserRepository userRepository,
                       ISenderService senderService,
                       ConversionService conversionService) {
        this.userRepository = userRepository;
        this.senderService = senderService;
        this.conversionService = conversionService;
    }

    @Override
    public void add(UserRegistrationDto user) {
        if (user == null) {
            throw new DtoNullPointerException("userRegistrationDto must not be null");
        }
        UserEntity userEntity = conversionService.convert(user, UserEntity.class);
        userEntity = userRepository.save(userEntity);
        senderService.addVerificationMail(userEntity);
    }

    @Override
    public void verification(UserVerificationDto user) {
        if (user == null) {
            throw new DtoNullPointerException("userVerificationDto must not be null");
        }
        Optional<UserEntity> userEntityOptional = userRepository.findByMail(user.getMail());
        UserEntity userEntity = userEntityOptional.orElseThrow(
                () -> new VerificationException("verification error"));
        MailEntity mail = senderService.getMail(userEntity, user.getUuid());
        if (mail == null) {
            throw new VerificationException("verification error");
        } else {
            userEntity.setStatus(new UserStatusEntity(UserStatus.ACTIVATED));
            userRepository.save(userEntity);
            senderService.addRegistrationCompleteMail(userEntity);
        }
    }

    @Override
    public void login(UserLoginDto user) {
        if (user == null) {
            throw new DtoNullPointerException("userLoginDto must not be null");
        }
        Optional<UserEntity> userEntityOptional = userRepository.findByMail(user.getMail());
        UserEntity userEntity = userEntityOptional.orElseThrow(
                () -> new EntityNotFoundException("user with entered email not found"));
        if (!userEntity.getStatus().getStatus().equals(UserStatus.ACTIVATED)) {
            throw new AuthorizationException("user is not active");
        }
        if (userEntity.getPassword().equals(user.getPassword())) {
            //
        } else {
            throw new AuthorizationException(
                    "username and password combination was entered incorrectly");
        }
    }

    @Override
    public PageUserDto get() {
        return null;
    }
}