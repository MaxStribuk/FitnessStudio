package by.itacademy.service.impl;

import by.itacademy.aop.api.Auditable;
import by.itacademy.core.dto.request.ProductCreateDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageProductDto;
import by.itacademy.core.enums.EssenceType;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
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
        ProductEntity productEntity = this.conversionService.convert(product, ProductEntity.class);
        this.productRepository.save(productEntity);
    }

    @Override
    public PageDto<PageProductDto> getAll(Pageable pageable) {
        Page<ProductEntity> products = this.productRepository.findAll(pageable);
        return this.productPageDtoConverter.convert(products);
    }

    @Override
    @Auditable(value = "updated product information", type = EssenceType.PRODUCT)
    public void update(UUID uuid, LocalDateTime dtUpdate, ProductCreateDto product) {
        Optional<ProductEntity> productEntityOptional = this.productRepository.findById(uuid);
        ProductEntity productEntity = productEntityOptional.orElseThrow(
                () -> new EntityNotFoundException("product not found: " + uuid));
        if (!dtUpdate.isEqual(productEntity.getDtUpdate())) {
            throw new InvalidVersionException("invalid dtUpdate");
        }
        update(productEntity, product);
        this.productRepository.save(productEntity);
    }

    @Override
    public PageProductDto get(UUID uuid) {
        Optional<ProductEntity> productEntityOptional = this.productRepository.findById(uuid);
        ProductEntity productEntity = productEntityOptional.orElseThrow(
                () -> new EntityNotFoundException("product not found: " + uuid));
        return this.conversionService.convert(productEntity, PageProductDto.class);
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