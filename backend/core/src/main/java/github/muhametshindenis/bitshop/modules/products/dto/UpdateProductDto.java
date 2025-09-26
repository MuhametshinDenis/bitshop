package github.muhametshindenis.bitshop.modules.products.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 18.09.2025 September 2025
 */
@Data
public class UpdateProductDto {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private Long categoryId;
    private Map<String, Object> attributes;
}
