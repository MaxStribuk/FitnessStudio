package by.itacademy.web.controller;

import by.itacademy.core.Constants;
import by.itacademy.core.dto.request.ProductCreateDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageProductDto;
import by.itacademy.service.api.IProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
@Validated
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(@RequestBody @Valid ProductCreateDto product) {
        this.productService.add(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageDto<PageProductDto>> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @PositiveOrZero(message = "page must be greater than or equal to 0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "20")
            @Positive(message = "size must be greater than 0") int size) {
        Pageable pageable = PageRequest.of(page, size);
        PageDto<PageProductDto> pageProducts = this.productService.getAll(pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pageProducts);
    }

    @PutMapping(path = "{uuid}/dt_update/{dt_update}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(
            @PathVariable(name = "uuid")
            @Pattern(regexp = Constants.UUID_PATTERN, message = "invalid uuid") String uuid,
            @PathVariable(name = "dt_update")
            @Past(message = "invalid dtUpdate") LocalDateTime dtUpdate,
            @RequestBody @Valid ProductCreateDto product) {
        this.productService.update(UUID.fromString(uuid), dtUpdate, product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}