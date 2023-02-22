package by.itacademy.service.impl;

import by.itacademy.core.dto.request.UserCreateDto;
import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.dao.api.IAdminRepository;
import by.itacademy.dao.entity.UserEntity;
import by.itacademy.service.api.IConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import by.itacademy.service.api.IAdminService;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminService implements IAdminService {

    private final IAdminRepository adminRepository;
    private final Converter<UserCreateDto, UserEntity> userCreateDtoEntityConverter;
    private final Converter<UserEntity, PageUserDto> userEntityPageDtoConverter;
    private final IConverter<UserEntity, PageUserDto> entityPageDtoConverter;

    public AdminService(IAdminRepository adminRepository,
                        Converter<UserCreateDto, UserEntity> userCreateDtoEntityConverter,
                        Converter<UserEntity, PageUserDto> userEntityPageDtoConverter,
                        IConverter<UserEntity, PageUserDto> entityPageDtoConverter) {
        this.adminRepository = adminRepository;
        this.userCreateDtoEntityConverter = userCreateDtoEntityConverter;
        this.userEntityPageDtoConverter = userEntityPageDtoConverter;
        this.entityPageDtoConverter = entityPageDtoConverter;
    }

    @Override
    public void add(UserCreateDto user) {
        UserEntity userEntity = userCreateDtoEntityConverter.convert(user);
        adminRepository.save(userEntity);
    }

    @Override
    public PageDto<PageUserDto> getAll(Pageable pageable) {
        Page<UserEntity> users = adminRepository.findAll(pageable);
        return entityPageDtoConverter.convert(users, userEntityPageDtoConverter);
    }

    @Override
    public PageUserDto get(UUID uuid) {
        Optional<UserEntity> userEntityOptional = adminRepository.findById(uuid);
        UserEntity userEntity = userEntityOptional.orElseThrow(IllegalArgumentException::new);
        return userEntityPageDtoConverter.convert(userEntity);
    }

    @Override
    public void update(UUID uuid, int dtUpdate, UserCreateDto user) {
        Optional<UserEntity> userEntityOptional = adminRepository.findById(uuid);
        UserEntity userEntity = userEntityOptional.orElseThrow(IllegalArgumentException::new);
        if (dtUpdate != userEntity.getDtUpdate().toEpochSecond(ZoneOffset.UTC)) {
            throw new IllegalArgumentException();
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