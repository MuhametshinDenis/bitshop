package github.muhametshindenis.bitshop.modules.products.controller;

import github.muhametshindenis.bitshop.common.dto.ResponseMessage;
import github.muhametshindenis.bitshop.modules.products.dto.CreateProductDto;
import github.muhametshindenis.bitshop.modules.products.dto.ProductFilterDto;
import github.muhametshindenis.bitshop.modules.products.dto.UpdateProductDto;
import github.muhametshindenis.bitshop.modules.products.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 15.09.2025 September 2025
 */
@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Products", description = "API для управления продуктами: создание, обновление, удаление, поиск")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Создать продукт", description = "Создает новый продукт с необязательным изображением")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Продукт успешно создан"), @ApiResponse(responseCode = "400", description = "Некорректные данные"), @ApiResponse(responseCode = "409", description = "Продукт с таким именем уже существует")})
    @PostMapping
    public ResponseEntity<?> create(@Parameter(description = "Данные продукта", required = true) @Valid @RequestPart("product") CreateProductDto createProductDto,
                                    @Parameter(description = "Изображение продукта") @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.create(createProductDto, image));
    }

    @Operation(summary = "Получить продукт по ID", description = "Возвращает продукт по указанному ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Продукт найден"), @ApiResponse(responseCode = "404", description = "Продукт не найден")})
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@Parameter(description = "ID продукта") @PathVariable("id") Long productId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.findById(productId));
    }

    @Operation(summary = "Поиск продуктов по фильтрам", description = "Возвращает список продуктов по заданным фильтрам")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Список продуктов получен")})
    @GetMapping
    public ResponseEntity<?> findByFilters(@ParameterObject @ModelAttribute ProductFilterDto productFilterDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.findByFilters(productFilterDto));
    }

    @Operation(summary = "Обновить продукт", description = "Обновляет продукт по ID с возможностью изменения изображения")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Продукт успешно обновлен"), @ApiResponse(responseCode = "404", description = "Продукт не найден")})
    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@Parameter(description = "Данные для обновления продукта") @RequestPart(value = "product", required = false) UpdateProductDto updateProductDto,
                                    @Parameter(description = "Новое изображение продукта") @RequestPart(name = "image", required = false) MultipartFile image,
                                    @Parameter(description = "ID продукта", required = true) @PathVariable(value = "id") Long productId) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.update(updateProductDto, image, productId));
    }

    @Operation(summary = "Удалить продукт", description = "Удаляет продукт по указанному ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Продукт успешно удален"), @ApiResponse(responseCode = "404", description = "Продукт не найден")})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Parameter(description = "ID продукта", required = true) @PathVariable("id") Long productId) {
        this.productService.deleteById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Продукт c ID: " + productId + " успешно удален"));
    }
}
