package github.muhametshindenis.bitshop.modules.categories.controller;

import github.muhametshindenis.bitshop.common.dto.ResponseMessage;
import github.muhametshindenis.bitshop.modules.categories.dto.CreateCategoryDto;
import github.muhametshindenis.bitshop.modules.categories.dto.UpdateCategoryDto;
import github.muhametshindenis.bitshop.modules.categories.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 18.09.2025 September 2025
 */
@RestController
@RequestMapping("/api/v1/categories")
@Tag(name = "Categories", description = "API для управления категориями")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Operation(summary = "Создать категорию", description = "Создает новую категорию с указанными данными")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Категория успешно создана"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные для создания категории"),
            @ApiResponse(responseCode = "409", description = "Категория с таким именем уже существует")
    })
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateCategoryDto createCategoryDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(createCategoryDto));
    }

    @Operation(summary = "Получить все категории", description = "Возвращает список всех категорий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список категорий успешно получен")
    })
    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
    }

    @Operation(summary = "Получить категорию по ID", description = "Возвращает категорию по указанному ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Категория успешно найдена"),
            @ApiResponse(responseCode = "404", description = "Категория с указанным ID не найдена")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long categoryId) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findById(categoryId));
    }

    @Operation(summary = "Обновить категорию", description = "Обновляет данные категории по указанному ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Категория успешно обновлена"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные для обновления категории"),
            @ApiResponse(responseCode = "404", description = "Категория с указанным ID не найдена")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long categoryId, @Valid @RequestBody UpdateCategoryDto updateCategoryDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.update(updateCategoryDto, categoryId));
    }

    @Operation(summary = "Удалить категорию", description = "Удаляет категорию по указанному ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Категория успешно удалена"),
            @ApiResponse(responseCode = "404", description = "Категория с указанным ID не найдена")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long categoryId) {
        this.categoryService.deleteById(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Категория с ID: " + categoryId + " успешно удалена!"));
    }
}