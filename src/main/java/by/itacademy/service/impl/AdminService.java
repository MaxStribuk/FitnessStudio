package by.itacademy.service.impl;

import by.itacademy.core.dto.request.UserCreateDto;
import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.exceptions.DtoNullPointerException;
import by.itacademy.core.exceptions.EntityNotFoundException;
import by.itacademy.core.exceptions.InvalidVersionException;
import by.itacademy.repository.api.IAdminRepository;
import by.itacademy.repository.entity.UserEntity;
import by.itacademy.service.api.IConverter;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import by.itacademy.service.api.IAdminService;

import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

public class AdminService implements IAdminService {

    private final IAdminRepository adminRepository;
    private final ConversionService conversionService;
    private final Converter<UserEntity, PageUserDto> userEntityPageDtoConverter;
    private final IConverter<UserEntity, PageUserDto> entityPageDtoConverter;

    public AdminService(IAdminRepository adminRepository,
                        Converter<UserEntity, PageUserDto> userEntityPageDtoConverter,
                        IConverter<UserEntity, PageUserDto> entityPageDtoConverter,
                        ConversionService conversionService) {
        this.adminRepository = adminRepository;
        this.userEntityPageDtoConverter = userEntityPageDtoConverter;
        this.entityPageDtoConverter = entityPageDtoConverter;
        this.conversionService = conversionService;
    }

    @Override
    public void add(UserCreateDto user) {
        if (user == null) {
            throw new DtoNullPointerException("userCreateDto must not be null");
        }
        UserEntity userEntity = conversionService.convert(user, UserEntity.class);
        adminRepository.save(userEntity);
    }

    @Override
    public PageDto<PageUserDto> getAll(Pageable pageable) {
        if (pageable == null) {
            throw new NullPointerException("pageable must be not null");
        }
        Page<UserEntity> users = adminRepository.findAll(pageable);
        return entityPageDtoConverter.convert(users, userEntityPageDtoConverter);
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
    public void update(UUID uuid, long dtUpdate, UserCreateDto user) {
        if (uuid == null) {
            throw new EntityNotFoundException("invalid uuid");
        }
        if (user == null) {
            throw new DtoNullPointerException("userCreateDto must not be null");
        }
        Optional<UserEntity> userEntityOptional = adminRepository.findById(uuid);
        UserEntity userEntity = userEntityOptional.orElseThrow(
                () -> new EntityNotFoundException("user with uuid " + uuid + " not found"));
        if (dtUpdate != userEntity.getDtUpdate().toEpochSecond(ZoneOffset.UTC)) {
            throw new InvalidVersionException("invalid dtUpdate");
        }
        update(userEntity, user);
        adminRepository.save(userEntity);
    }

    private void update(UserEntity userEntity, UserCreateDto user) {
        userEntity.setFio(user.getFio());
        userEntity.setMail(user.getMail());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());
        userEntity.setStatus(user.getStatus());
    }
}