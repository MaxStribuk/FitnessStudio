package by.itacademy.service.impl;

import by.itacademy.core.dto.request.ProductCreateDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageProductDto;
import by.itacademy.core.exceptions.DtoNullPointerException;
import by.itacademy.core.exceptions.EntityNotFoundException;
import by.itacademy.core.exceptions.InvalidVersionException;
import by.itacademy.repository.api.IProductRepository;
import by.itacademy.repository.entity.ProductEntity;
import by.itacademy.service.api.IProductService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final ConversionService conversionService;

    public ProductService(IProductRepository productRepository,
                          ConversionService conversionService) {
        this.productRepository = productRepository;
        this.conversionService = conversionService;

    }

    @Override
    public void add(ProductCreateDto product) {
        if (product == null) {
            throw new DtoNullPointerException("productCreateDto must not be null");
        }
        ProductEntity productEntity = conversionService.convert(product, ProductEntity.class);
        productRepository.save(productEntity);
    }

    @Override
    public PageDto<PageProductDto> getAll(Pageable pageable) {
        if (pageable == null) {
            throw new NullPointerException("pageable must be not null");
        }
        Page<ProductEntity> products = productRepository.findAll(pageable);
        return conversionService.convert(products, PageDto.class);
    }

    @Override
    public void update(UUID uuid, LocalDateTime dtUpdate, ProductCreateDto product) {
        if (uuid == null) {
            throw new EntityNotFoundException("invalid uuid");
        }
        if (product == null) {
            throw new DtoNullPointerException("productCreateDto must not be null");
        }
        Optional<ProductEntity> productEntityOptional = productRepository.findById(uuid);
        ProductEntity productEntity = productEntityOptional.orElseThrow(
                () -> new EntityNotFoundException("product with uuid " + uuid + " not found"));
        if (!dtUpdate.isEqual(productEntity.getDtUpdate())) {
            throw new InvalidVersionException("invalid dtUpdate");
        }
        update(productEntity, product);
        productRepository.save(productEntity);
    }

    private void update(ProductEntity productEntity, ProductCreateDto product) {
        productEntity.setTitle(product.getTitle());
        productEntity.setWeight(product.getWeight());
        productEntity.setCalories(product.getCalories());
        productEntity.setProteins(product.getProteins());
        productEntity.setFats(product.getFats());
        productEntity.setCarbohydrates(product.getCarbohydrates());
    }
}