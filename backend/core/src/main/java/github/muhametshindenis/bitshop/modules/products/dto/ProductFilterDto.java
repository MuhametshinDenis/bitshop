package github.muhametshindenis.bitshop.modules.products.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
public record ProductFilterDto(
        @Schema(description = "ID категории для фильтрации", example = "1")
        Long categoryId,

        @Schema(description = "Минимальная цена", example = "10.5")
        BigDecimal minPrice,

        @Schema(description = "Максимальная цена", example = "100.0")
        BigDecimal maxPrice,

        @Schema(description = "Номер страницы (по умолчанию 1)", example = "1")
        Integer page,

        @Schema(description = "Размер страницы (по умолчанию 20)", example = "20")
        Integer pageSize
) {
    public ProductFilterDto(
            Long categoryId,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            Integer page,
            Integer pageSize
    ) {
        this.categoryId = categoryId;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.page = page != null ? page : 1;
        this.pageSize = pageSize != null ? pageSize : 20;
    }
}
