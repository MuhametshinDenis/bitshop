package github.muhametshindenis.bitshop.modules.products.dto;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 15.09.2025 September 2025
 */
public record ProductResponseDto(Long id, String name, String description, BigDecimal price, Integer quantity,
                                 String imageUrl, String category, String brand, Map<String, Object> attributes) {
}
