package by.itacademy.service.impl;

import by.itacademy.core.dto.request.UserCreateDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.core.enums.EssenceType;
import by.itacademy.core.exception.DtoNullPointerException;
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

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

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
    @Auditable(value = "added new user", type = EssenceType.USER)
    public void add(UserCreateDto user) {
        if (user == null) {
            throw new DtoNullPointerException("userCreateDto must not be null");
        }
        UserEntity userEntity = conversionService.convert(user, UserEntity.class);
        userEntity.setPassword(encoder.encode(user.getPassword()));
        adminRepository.save(userEntity);
    }

    @Override
    public PageDto<PageUserDto> getAll(Pageable pageable) {
        if (pageable == null) {
            throw new NullPointerException("pageable must be not null");
        }
        Page<UserEntity> users = adminRepository.findAll(pageable);
        return userPageDtoConverter.convert(users);
    }

    @Override
    public PageUserDto get(UUID uuid) {
        if (uuid == null) {
            throw new EntityNotFoundException("invalid uuid");
        }
        Optional<UserEntity> userEntityOptional = adminRepository.findById(uuid);
        UserEntity userEntity = userEntityOptional.orElseThrow(
                () -> new EntityNotFoundException("user with uuid " + uuid + " not found"));
        return conversionService.convert(userEntity, PageUserDto.class);
    }

    @Override
    @Auditable(value = "updated user information", type = EssenceType.USER)
    public void update(UUID uuid, LocalDateTime dtUpdate, UserCreateDto user) {
        if (uuid == null) {
            throw new EntityNotFoundException("invalid uuid");
        }
        if (user == null) {
            throw new DtoNullPointerException("userCreateDto must not be null");
        }
        if (dtUpdate == null) {
            throw new InvalidVersionException("invalid dtUpdate");
        }
        Optional<UserEntity> userEntityOptional = adminRepository.findById(uuid);
        UserEntity userEntity = userEntityOptional.orElseThrow(
                () -> new EntityNotFoundException("user with uuid " + uuid + " not found"));
        if (! dtUpdate.isEqual(userEntity.getDtUpdate())) {
            throw new InvalidVersionException("invalid dtUpdate");
        }
        update(userEntity, user);
        adminRepository.save(userEntity);
    }

    private void update(UserEntity userEntity, UserCreateDto user) {
        userEntity.setFio(user.getFio());
        userEntity.setMail(user.getMail());
        userEntity.setPassword(encoder.encode(user.getPassword()));
        userEntity.setRole(new UserRoleEntity(user.getRole()));
        userEntity.setStatus(new UserStatusEntity(user.getStatus()));
    }
}