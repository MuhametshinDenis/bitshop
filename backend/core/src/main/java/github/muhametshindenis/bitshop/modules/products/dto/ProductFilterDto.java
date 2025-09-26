package github.muhametshindenis.bitshop.modules.products.dto;

import java.math.BigDecimal;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
public record ProductFilterDto(
        Long categoryId,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        Integer page,
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
