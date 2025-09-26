package github.muhametshindenis.bitshop.modules.categories.service;

import github.muhametshindenis.bitshop.modules.categories.dto.CategoryResponseDto;
import github.muhametshindenis.bitshop.modules.categories.dto.CreateCategoryDto;
import github.muhametshindenis.bitshop.modules.categories.dto.UpdateCategoryDto;
import github.muhametshindenis.bitshop.modules.categories.entity.Category;

import java.util.List;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 18.09.2025 September 2025
 */
public interface CategoryService {
    CategoryResponseDto create(CreateCategoryDto createCategoryDto);
    List<CategoryResponseDto> findAll();
    CategoryResponseDto findById(Long categoryId);
    CategoryResponseDto update(UpdateCategoryDto updateCategoryDto, Long categoryId);
    void deleteById(Long categoryId);
    Category findCategoryById(Long categoryId);
}
