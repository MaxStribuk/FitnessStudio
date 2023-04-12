package by.itacademy.service.impl;

import by.itacademy.aop.api.Auditable;
import by.itacademy.core.dto.request.ProductCreateDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageProductDto;
import by.itacademy.core.enums.EssenceType;
import by.itacademy.core.exception.DtoNullPointerException;
import by.itacademy.core.exception.EntityNotFoundException;
import by.itacademy.core.exception.InvalidVersionException;
import by.itacademy.repository.api.IProductRepository;
import by.itacademy.repository.entity.ProductEntity;
import by.itacademy.service.api.IProductService;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final ConversionService conversionService;
    private final Converter<Page<ProductEntity>, PageDto<PageProductDto>> productPageDtoConverter;

    public ProductService(IProductRepository productRepository,
                          ConversionService conversionService,
                          Converter<Page<ProductEntity>, PageDto<PageProductDto>> productPageDtoConverter) {
        this.productRepository = productRepository;
        this.conversionService = conversionService;
        this.productPageDtoConverter = productPageDtoConverter;
    }

    @Override
    @Auditable(value = "added new product", type = EssenceType.PRODUCT)
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
        return productPageDtoConverter.convert(products);
    }

    @Override
    @Auditable(value = "updated product information", type = EssenceType.PRODUCT)
    public void update(UUID uuid, LocalDateTime dtUpdate, ProductCreateDto product) {
        if (uuid == null) {
            throw new EntityNotFoundException("invalid uuid");
        }
        if (product == null) {
            throw new DtoNullPointerException("productCreateDto must not be null");
        }
        if (dtUpdate == null) {
            throw new InvalidVersionException("invalid dtUpdate");
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

    @Override
    public PageProductDto get(UUID uuid) {
        if (uuid == null) {
            throw new EntityNotFoundException("invalid uuid");
        }
        Optional<ProductEntity> productEntityOptional = productRepository.findById(uuid);
        ProductEntity productEntity = productEntityOptional.orElseThrow(
                () -> new EntityNotFoundException("product with uuid " + uuid + " not found"));
        return conversionService.convert(productEntity, PageProductDto.class);
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