package github.muhametshindenis.bitshop.modules.brands.controller;

import github.muhametshindenis.bitshop.common.dto.ResponseMessage;
import github.muhametshindenis.bitshop.modules.brands.dto.BrandResponseDto;
import github.muhametshindenis.bitshop.modules.brands.dto.CreateBrandDto;
import github.muhametshindenis.bitshop.modules.brands.dto.UpdateBrandDto;
import github.muhametshindenis.bitshop.modules.brands.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
@RestController
@RequestMapping("/api/v1/brands")
@Tag(name = "Brands", description = "API для управления брендами")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @Operation( summary = "Создать бренд", description = "Создаёт новый бренд. Поддерживает загрузку изображения (multipart/form-data).", responses = { @ApiResponse(responseCode = "201", description = "Бренд успешно создан", content = @Content(schema = @Schema(implementation = CreateBrandDto.class))), @ApiResponse(responseCode = "400", description = "Некорректные данные", content = @Content) } )
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestPart(value = "brand") CreateBrandDto createBrandDto, @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.brandService.create(createBrandDto, image));
    }

    @Operation( summary = "Получить список брендов", description = "Возвращает список всех брендов.", responses = { @ApiResponse(responseCode = "200", description = "Бренды успешно получены") } )
    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.brandService.findALl());
    }

    @Operation( summary = "Получить бренд по ID", description = "Возвращает информацию о бренде по его идентификатору.", responses = { @ApiResponse(responseCode = "200", description = "Бренд найден"), @ApiResponse(responseCode = "404", description = "Бренд не найден", content = @Content) } )
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long brandId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.brandService.findById(brandId));
    }

    @Operation( summary = "Обновить бренд", description = "Обновляет данные бренда и/или изображение по ID.", responses = { @ApiResponse(responseCode = "200", description = "Бренд успешно обновлён", content = @Content(schema = @Schema(implementation = BrandResponseDto.class))), @ApiResponse(responseCode = "404", description = "Бренд не найден", content = @Content) } )
    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@RequestPart(value = "brand", required = false)UpdateBrandDto updateBrandDto,
                                    @RequestPart(value = "image", required = false) MultipartFile image,
                                    @PathVariable("id") Long brandId) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(this.brandService.update(updateBrandDto, image, brandId));
    }

    @Operation( summary = "Удалить бренд", description = "Удаляет бренд по ID.", responses = { @ApiResponse(responseCode = "200", description = "Бренд успешно удалён", content = @Content(schema = @Schema(implementation = ResponseMessage.class))), @ApiResponse(responseCode = "404", description = "Бренд не найден", content = @Content) } )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long brandId) {
        this.brandService.deleteById(brandId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(
                "Бренд с ID: " + brandId + " успешно удален!"
        ));
    }
}
