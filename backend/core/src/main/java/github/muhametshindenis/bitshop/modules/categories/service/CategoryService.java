package github.muhametshindenis.bitshop.modules.categories.service;

import github.muhametshindenis.bitshop.common.exception.ConflictException;
import github.muhametshindenis.bitshop.common.exception.NotFoundException;
import github.muhametshindenis.bitshop.modules.categories.dto.CategoryResponseDto;
import github.muhametshindenis.bitshop.modules.categories.dto.CreateCategoryDto;
import github.muhametshindenis.bitshop.modules.categories.dto.UpdateCategoryDto;
import github.muhametshindenis.bitshop.modules.categories.entity.Category;

import java.util.List;

/**
 * Сервис для работы с категориями товаров.
 * <p>
 * Предоставляет методы для:
 * <ul>
 *     <li>Создания новой категории;</li>
 *     <li>Получения списка всех категорий;</li>
 *     <li>Получения категории по ID;</li>
 *     <li>Обновления категории;</li>
 *     <li>Удаления категории;</li>
 *     <li>Получения сущности {@link Category} по ID (для внутренних нужд).</li>
 * </ul>
 * <p>
 * Реализация должна обрабатывать исключения:
 * <ul>
 *     <li>{@link NotFoundException} — если категория не найдена;</li>
 *     <li>{@link ConflictException} — если создается категория с уже существующим именем.</li>
 * </ul>
 *
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 18.09.2025 September 2025
 */
public interface CategoryService {
    /**
     * Создает новую категорию.
     *
     * @param createCategoryDto DTO с данными категории
     * @return DTO созданной категории
     * @throws ConflictException если категория с таким именем уже существует
     */
    CategoryResponseDto create(CreateCategoryDto createCategoryDto);

    /**
     * Получает список всех категорий.
     *
     * @return список DTO всех категорий
     */
    List<CategoryResponseDto> findAll();

    /**
     * Получает категорию по ID.
     *
     * @param categoryId ID категории
     * @return DTO категории
     * @throws NotFoundException если категория с указанным ID не найдена
     */
    CategoryResponseDto findById(Long categoryId);

    /**
     * Обновляет данные категории по ID.
     *
     * @param updateCategoryDto DTO с обновленными данными
     * @param categoryId ID категории
     * @return DTO обновленной категории
     * @throws NotFoundException если категория с указанным ID не найдена
     */
    CategoryResponseDto update(UpdateCategoryDto updateCategoryDto, Long categoryId);

    /**
     * Удаляет категорию по ID.
     *
     * @param categoryId ID категории
     * @throws NotFoundException если категория с указанным ID не найдена
     */
    void deleteById(Long categoryId);

    /**
     * Получает сущность {@link Category} по ID.
     * Используется для внутренних нужд (например, при создании или обновлении других сущностей).
     *
     * @param categoryId ID категории
     * @return сущность Category
     * @throws NotFoundException если категория с указанным ID не найдена
     */
    Category findCategoryById(Long categoryId);
}
