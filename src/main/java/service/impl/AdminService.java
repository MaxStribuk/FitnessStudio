package service.impl;

import core.dto.request.UserCreateDto;
import core.dto.response.PageUserDto;
import core.dto.response.PageUsersDto;
import dao.api.IAdminDao;
import dao.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import service.api.IAdminService;

import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

public class AdminService implements IAdminService {

    private final IAdminDao adminDao;
    private final Converter<UserCreateDto, UserEntity> userCreateDtoEntityConverter;
    private final Converter<UserEntity, PageUserDto> userEntityPageDtoConverter;
    private final Converter<Page<UserEntity>, PageUsersDto> usersEntityPagesDtoConverter;

    public AdminService(IAdminDao adminDao,
                        Converter<UserCreateDto, UserEntity> userCreateDtoEntityConverter,
                        Converter<UserEntity, PageUserDto> userEntityPageDtoConverter,
                        Converter<Page<UserEntity>, PageUsersDto> usersEntityPagesDtoConverter) {
        this.adminDao = adminDao;
        this.userCreateDtoEntityConverter = userCreateDtoEntityConverter;
        this.userEntityPageDtoConverter = userEntityPageDtoConverter;
        this.usersEntityPagesDtoConverter = usersEntityPagesDtoConverter;
    }

    @Override
    public void add(UserCreateDto user) {
        UserEntity userEntity = userCreateDtoEntityConverter.convert(user);
        adminDao.save(userEntity);
    }

    @Override
    public PageUsersDto getAll(Pageable pageable) {
        Page<UserEntity> users = adminDao.findAll(pageable);
        return usersEntityPagesDtoConverter.convert(users);
    }

    @Override
    public PageUserDto get(UUID uuid) {
        Optional<UserEntity> userEntityOptional = adminDao.findById(uuid);
        UserEntity userEntity = userEntityOptional.orElseThrow(IllegalArgumentException::new);
        return userEntityPageDtoConverter.convert(userEntity);
    }

    @Override
    public void update(UUID uuid, int dtUpdate, UserCreateDto user) {
        Optional<UserEntity> userEntityOptional = adminDao.findById(uuid);
        UserEntity userEntity = userEntityOptional.orElseThrow(IllegalArgumentException::new);
        if (dtUpdate != userEntity.getDtUpdate().toEpochSecond(ZoneOffset.UTC)) {
            throw new IllegalArgumentException();
        }
        update(userEntity, user);
        adminDao.save(userEntity);
    }

    private void update(UserEntity userEntity, UserCreateDto user) {
        userEntity.setFio(user.getFio());
        userEntity.setMail(user.getMail());
        userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());
        userEntity.setStatus(user.getStatus());
    }
}