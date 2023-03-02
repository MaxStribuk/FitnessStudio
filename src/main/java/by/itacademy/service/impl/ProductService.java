package by.itacademy.service.impl;

import by.itacademy.core.dto.request.ProductCreateDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageProductDto;
import by.itacademy.core.exceptions.DtoNullPointerException;
import by.itacademy.core.exceptions.EntityNotFoundException;
import by.itacademy.core.exceptions.InvalidVersionException;
import by.itacademy.repository.api.IProductRepository;
import by.itacademy.repository.entity.ProductEntity;
import by.itacademy.service.api.IConverter;
import by.itacademy.service.api.IProductService;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final ConversionService conversionService;
    private final Converter<ProductEntity, PageProductDto> productEntityPageDtoConverter;
    private final IConverter<ProductEntity, PageProductDto> entityPageDtoConverter;


    public ProductService(IProductRepository productRepository,
                          ConversionService conversionService,
                          Converter<ProductEntity, PageProductDto> productEntityPageDtoConverter,
                          IConverter<ProductEntity, PageProductDto> entityPageDtoConverter) {
        this.productRepository = productRepository;
        this.conversionService = conversionService;
        this.productEntityPageDtoConverter = productEntityPageDtoConverter;
        this.entityPageDtoConverter = entityPageDtoConverter;
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
        return entityPageDtoConverter.convert(products, productEntityPageDtoConverter);
    }

    @Override
    public void update(UUID uuid, long dtUpdate, ProductCreateDto product) {
        if (uuid == null) {
            throw new EntityNotFoundException("invalid uuid");
        }
        if (product == null) {
            throw new DtoNullPointerException("productCreateDto must not be null");
        }
        Optional<ProductEntity> productEntityOptional = productRepository.findById(uuid);
        ProductEntity productEntity = productEntityOptional.orElseThrow(
                () -> new EntityNotFoundException("product with uuid " + uuid + " not found"));
        if (dtUpdate != productEntity.getDtUpdate().toEpochSecond(ZoneOffset.UTC)) {
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