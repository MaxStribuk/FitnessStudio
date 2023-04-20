package by.itacademy.service.impl;

import by.itacademy.core.dto.request.UserLoginDto;
import by.itacademy.core.dto.request.UserRegistrationDto;
import by.itacademy.core.dto.request.UserVerificationDto;
import by.itacademy.core.dto.transfer.CurrentUserDto;
import by.itacademy.core.dto.transfer.MailDto;
import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.core.enums.EmailSubject;
import by.itacademy.core.enums.UserStatus;
import by.itacademy.core.exception.AuthorizationException;
import by.itacademy.core.exception.EntityNotFoundException;
import by.itacademy.core.exception.VerificationException;
import by.itacademy.repository.api.IUserRepository;
import by.itacademy.repository.entity.UserEntity;
import by.itacademy.repository.entity.UserStatusEntity;
import by.itacademy.service.api.IJwtTokenUtil;
import by.itacademy.service.api.ISenderService;
import by.itacademy.service.api.IUserHolder;
import by.itacademy.service.api.IUserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final ISenderService senderService;
    private final ConversionService conversionService;
    private final PasswordEncoder encoder;
    private final IUserHolder holder;
    private final IJwtTokenUtil jwtTokenUtil;

    public UserService(IUserRepository userRepository, ISenderService senderService,
                       ConversionService conversionService, PasswordEncoder encoder,
                       IUserHolder holder, IJwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.senderService = senderService;
        this.conversionService = conversionService;
        this.encoder = encoder;
        this.holder = holder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    @Transactional
    public void create(UserRegistrationDto user) {
        UserEntity userEntity = this.conversionService.convert(user, UserEntity.class);
        userEntity.setPassword(this.encoder.encode(user.getPassword()));
        userEntity = this.userRepository.save(userEntity);
        PageUserDto pageUserDto = this.conversionService.convert(userEntity, PageUserDto.class);
        this.senderService.create(pageUserDto, EmailSubject.VERIFICATION.toString());
    }

    @Override
    @Transactional
    public void verify(UserVerificationDto user) {
        UserEntity userEntity = this.userRepository
                .findByMail(user.getMail())
                .orElseThrow(() -> new VerificationException("verification error"));
        MailDto mail = this.senderService.getMail(userEntity, user.getUuid());
        if (mail == null) {
            throw new VerificationException("verification error");
        } else {
            userEntity.setStatus(new UserStatusEntity(UserStatus.ACTIVATED));
            this.userRepository.save(userEntity);
            PageUserDto pageUserDto = this.conversionService.convert(userEntity, PageUserDto.class);
            this.senderService.create(pageUserDto, EmailSubject.REGISTRATION.toString());
        }
    }

    @Override
    @Transactional
    public String logIn(UserLoginDto user) {
        UserDetails userDetails = this.loadUserByMail(user.getMail());
        if (userDetails == null
                || !this.encoder.matches(user.getPassword(), userDetails.getPassword())) {
            throw new AuthorizationException(
                    "username and password combination was entered incorrectly");
        }
        CurrentUserDto currentUser = (CurrentUserDto) userDetails;
        if (currentUser.getStatus().equals(UserStatus.ACTIVATED)) {
            return this.jwtTokenUtil.generateAccessToken(userDetails);
        } else {
            throw new AuthorizationException("user must be activated");
        }
    }

    @Override
    @Transactional
    public PageUserDto get() {
        UserDetails user = this.holder.getCurrentUser();
        UserEntity userEntity = this.userRepository
                .findByMail(user.getUsername())
                .orElseThrow(() ->
                        new EntityNotFoundException("user with entered fio not found"));
        return this.conversionService.convert(userEntity, PageUserDto.class);
    }

    @Override
    @Transactional
    public UserDetails loadUserByMail(String mail) {
        Optional<UserEntity> userEntityOptional = this.userRepository.findByMail(mail);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            return this.conversionService.convert(userEntity, UserDetails.class);
        } else {
            return null;
        }
    }
}