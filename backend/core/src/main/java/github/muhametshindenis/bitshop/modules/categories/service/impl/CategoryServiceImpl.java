package github.muhametshindenis.bitshop.modules.categories.service.impl;

import github.muhametshindenis.bitshop.common.exception.ConflictException;
import github.muhametshindenis.bitshop.common.exception.NotFoundException;
import github.muhametshindenis.bitshop.modules.categories.dto.CategoryResponseDto;
import github.muhametshindenis.bitshop.modules.categories.dto.CreateCategoryDto;
import github.muhametshindenis.bitshop.modules.categories.dto.UpdateCategoryDto;
import github.muhametshindenis.bitshop.modules.categories.entity.Category;
import github.muhametshindenis.bitshop.modules.categories.mapper.CategoryMapper;
import github.muhametshindenis.bitshop.modules.categories.repository.CategoryRepository;
import github.muhametshindenis.bitshop.modules.categories.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 18.09.2025 September 2025
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponseDto create(CreateCategoryDto createCategoryDto) {
        if (categoryRepository.findByName(createCategoryDto.name()).isPresent()) {
            throw new ConflictException("Category with name " + createCategoryDto.name() + " already exists");
        }

        return CategoryMapper.toResponse(categoryRepository.save(CategoryMapper.toEntity(createCategoryDto)));
    }

    @Override
    public List<CategoryResponseDto> findAll() {
        return categoryRepository.findAll().stream().map(CategoryMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto findById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Категория с ID: " + categoryId + " не найдена!"));

        return CategoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponseDto update(UpdateCategoryDto updateCategoryDto, Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Категория с ID: " + categoryId + " не найдена!"));

        category.setName(updateCategoryDto.name());

        return CategoryMapper.toResponse(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Категория с ID: " + categoryId + " не найдена!"));

        this.categoryRepository.delete(category);
    }

    @Override
    public Category findCategoryById(Long categoryId) {
        return this.categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Категория с ID: " + categoryId + " не найдена!"));
    }
}
