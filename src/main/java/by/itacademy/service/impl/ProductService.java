package by.itacademy.service.impl;

import by.itacademy.core.dto.request.ProductCreateDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageProductDto;
import by.itacademy.service.api.IProductService;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public class ProductService implements IProductService {

    @Override
    public void add(ProductCreateDto product) {

    }

    @Override
    public PageDto<PageProductDto> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public void update(UUID uuid, long dtUpdate, ProductCreateDto product) {

    }
}