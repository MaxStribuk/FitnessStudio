package by.itacademy.service.api;

import by.itacademy.core.dto.request.ProductCreateDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageProductDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IProductService {

    void add(ProductCreateDto product);

    PageDto<PageProductDto> getAll(Pageable pageable);

    void update(UUID uuid, LocalDateTime dtUpdate, ProductCreateDto product);
}