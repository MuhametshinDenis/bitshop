package github.muhametshindenis.bitshop.modules.categories.controller;

import github.muhametshindenis.bitshop.common.dto.ApiResponse;
import github.muhametshindenis.bitshop.modules.categories.dto.CreateCategoryDto;
import github.muhametshindenis.bitshop.modules.categories.dto.UpdateCategoryDto;
import github.muhametshindenis.bitshop.modules.categories.service.CategoryService;
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
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateCategoryDto createCategoryDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(createCategoryDto));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long categoryId) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findById(categoryId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long categoryId, @Valid @RequestBody UpdateCategoryDto updateCategoryDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.update(updateCategoryDto, categoryId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long categoryId) {
        this.categoryService.deleteById(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Категория с ID: " + categoryId + " успешно удалена!"));
    }
}