package github.muhametshindenis.bitshop.modules.products.mapper;

import github.muhametshindenis.bitshop.modules.brands.entity.Brand;
import github.muhametshindenis.bitshop.modules.categories.entity.Category;
import github.muhametshindenis.bitshop.modules.products.dto.CreateProductDto;
import github.muhametshindenis.bitshop.modules.products.dto.ProductResponseDto;
import github.muhametshindenis.bitshop.modules.products.dto.UpdateProductDto;
import github.muhametshindenis.bitshop.modules.products.entity.Product;

import java.util.Optional;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 18.09.2025 September 2025
 */
public class ProductMapper {
    public static Product toEntity(CreateProductDto createProductDto) {
        return Product.builder()
                .name(createProductDto.getName())
                .description(createProductDto.getDescription())
                .price(createProductDto.getPrice())
                .quantity(createProductDto.getQuantity())
                .attributes(createProductDto.getAttributes())
                .build();
    }

    public static ProductResponseDto toResponse(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getImageUrl(),
                Optional.ofNullable(product.getCategory())
                        .map(Category::getName)
                        .orElse(null),
                Optional.ofNullable(product.getBrand())
                        .map(Brand::getName)
                        .orElse(null),
                product.getAttributes()
        );
    }

    /**
     * Обновляет поля сущности {@link Product} на основе данных из {@link UpdateProductDto}.
     * <p>
     * Все поля DTO проверяются на null и обновляются только если значение присутствует.
     * Этот метод отвечает исключительно за маппинг данных и не выполняет бизнес-логику
     * (например, изменение категории или загрузку изображения).
     *
     * @param dto     DTO с данными для обновления продукта
     * @param product сущность продукта, которая будет обновлена
     * @implNote Специально используется явное обновление через Optional.ofNullable(),
     * чтобы сохранить читаемость и избежать сложной динамической логики.
     */
    public static void updateFromDto(UpdateProductDto dto, Product product) {
        Optional.ofNullable(dto.getName()).ifPresent(product::setName);
        Optional.ofNullable(dto.getDescription()).ifPresent(product::setDescription);
        Optional.ofNullable(dto.getPrice()).ifPresent(product::setPrice);
        Optional.ofNullable(dto.getQuantity()).ifPresent(product::setQuantity);
        Optional.ofNullable(dto.getAttributes()).ifPresent(product::setAttributes);
    }

}
