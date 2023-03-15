package by.itacademy.service.impl;

import by.itacademy.core.dto.request.UserLoginDto;
import by.itacademy.core.dto.request.UserRegistrationDto;
import by.itacademy.core.dto.request.UserVerificationDto;
import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.core.enums.UserStatus;
import by.itacademy.core.exception.AuthorizationException;
import by.itacademy.core.exception.DtoNullPointerException;
import by.itacademy.core.exception.EntityNotFoundException;
import by.itacademy.core.exception.VerificationException;
import by.itacademy.repository.api.IUserRepository;
import by.itacademy.repository.entity.MailEntity;
import by.itacademy.repository.entity.UserEntity;
import by.itacademy.repository.entity.UserStatusEntity;
import by.itacademy.service.api.IJwtTokenUtil;
import by.itacademy.service.api.ISenderService;
import by.itacademy.service.api.IUserService;
import by.itacademy.service.util.UserHolder;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final ISenderService senderService;
    private final ConversionService conversionService;
    private final PasswordEncoder encoder;
    private final UserHolder holder;
    private final IJwtTokenUtil jwtTokenUtil;

    public UserService(IUserRepository userRepository,
                       ISenderService senderService,
                       ConversionService conversionService,
                       PasswordEncoder encoder,
                       UserHolder holder,
                       IJwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.senderService = senderService;
        this.conversionService = conversionService;
        this.encoder = encoder;
        this.holder = holder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public void add(UserRegistrationDto user) {
        if (user == null) {
            throw new DtoNullPointerException("userRegistrationDto must not be null");
        }
        UserEntity userEntity = conversionService.convert(user, UserEntity.class);
        userEntity.setPassword(encoder.encode(user.getPassword()));
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
    public String login(UserLoginDto user) {
        if (user == null) {
            throw new DtoNullPointerException("userLoginDto must not be null");
        }
        UserDetails userDetails = this.loadUserByMail(user.getMail());
        if (userDetails == null || !encoder.matches(user.getPassword(), userDetails.getPassword())) {
            throw new AuthorizationException(
                    "username and password combination was entered incorrectly");
        }
        return jwtTokenUtil.generateAccessToken(userDetails);
    }

    @Override
    public PageUserDto get() {
        UserDetails user = holder.getUser();
        Optional<UserEntity> userEntityOptional = userRepository.findByMail(user.getUsername());
        UserEntity userEntity = userEntityOptional.orElseThrow(
                () -> new EntityNotFoundException("user with entered fio not found"));
        return conversionService.convert(userEntity, PageUserDto.class);
    }

    @Override
    public UserDetails loadUserByMail(String mail) {
        if (mail == null) {
            return null;
        }
        Optional<UserEntity> userEntityOptional = userRepository.findByMail(mail);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            return conversionService.convert(userEntity, UserDetails.class);
        } else {
            return null;
        }
    }
}