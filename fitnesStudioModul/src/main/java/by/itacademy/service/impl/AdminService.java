package by.itacademy.service.impl;

import by.itacademy.core.dto.request.UserCreateDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.core.enums.EssenceType;
import by.itacademy.core.exception.EntityNotFoundException;
import by.itacademy.core.exception.InvalidVersionException;
import by.itacademy.repository.api.IAdminRepository;
import by.itacademy.repository.entity.UserEntity;
import by.itacademy.repository.entity.UserRoleEntity;
import by.itacademy.repository.entity.UserStatusEntity;
import by.itacademy.service.api.IAdminService;
import by.itacademy.aop.api.Auditable;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminService implements IAdminService {

    private final IAdminRepository adminRepository;
    private final ConversionService conversionService;
    private final Converter<Page<UserEntity>, PageDto<PageUserDto>> userPageDtoConverter;
    private final PasswordEncoder encoder;

    public AdminService(IAdminRepository adminRepository,
                        ConversionService conversionService,
                        Converter<Page<UserEntity>, PageDto<PageUserDto>> userPageDtoConverter,
                        PasswordEncoder encoder) {
        this.adminRepository = adminRepository;
        this.conversionService = conversionService;
        this.userPageDtoConverter = userPageDtoConverter;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    @Auditable(value = "added new user", type = EssenceType.USER)
    public void add(UserCreateDto user) {
        UserEntity userEntity = this.conversionService.convert(user, UserEntity.class);
        userEntity.setPassword(this.encoder.encode(user.getPassword()));
        this.adminRepository.save(userEntity);
    }

    @Override
    @Transactional
    public PageDto<PageUserDto> getAll(Pageable pageable) {
        Page<UserEntity> users = this.adminRepository.findAll(pageable);
        return this.userPageDtoConverter.convert(users);
    }

    @Override
    @Transactional
    public PageUserDto get(UUID uuid) {
        Optional<UserEntity> userEntityOptional = this.adminRepository.findById(uuid);
        UserEntity userEntity = userEntityOptional.orElseThrow(
                () -> new EntityNotFoundException("user not found: " + uuid));
        return this.conversionService.convert(userEntity, PageUserDto.class);
    }

    @Override
    @Transactional
    @Auditable(value = "updated user information", type = EssenceType.USER)
    public void update(UUID uuid, LocalDateTime dtUpdate, UserCreateDto user) {
        Optional<UserEntity> userEntityOptional = this.adminRepository.findById(uuid);
        UserEntity userEntity = userEntityOptional.orElseThrow(
                () -> new EntityNotFoundException("user not found: " + uuid));
        if (!dtUpdate.isEqual(userEntity.getDtUpdate())) {
            throw new InvalidVersionException("invalid dtUpdate");
        }
        update(userEntity, user);
        this.adminRepository.save(userEntity);
    }

    private void update(UserEntity userEntity, UserCreateDto user) {
        userEntity.setFio(user.getFio());
        userEntity.setMail(user.getMail());
        userEntity.setPassword(this.encoder.encode(user.getPassword()));
        userEntity.setRole(new UserRoleEntity(user.getRole()));
        userEntity.setStatus(new UserStatusEntity(user.getStatus()));
    }
}