package github.muhametshindenis.bitshop.modules.categories.mapper;

import github.muhametshindenis.bitshop.modules.categories.dto.CategoryResponseDto;
import github.muhametshindenis.bitshop.modules.categories.dto.CreateCategoryDto;
import github.muhametshindenis.bitshop.modules.categories.entity.Category;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 18.09.2025 September 2025
 */
public class CategoryMapper {
    public static Category toEntity(CreateCategoryDto createCategoryDto) {
        return Category.builder().name(createCategoryDto.name()).build();
    }

    public static CategoryResponseDto toResponse(Category category) {
        return new CategoryResponseDto(category.getId(), category.getName());
    }
}
