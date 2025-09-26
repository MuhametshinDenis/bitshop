package github.muhametshindenis.bitshop.modules.products.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 15.09.2025 September 2025
 */
@Data
public class CreateProductDto {
        @NotBlank(message = "Поле name является обязательным!")
        private String name;
        @NotBlank(message = "Поле description является обязательным!")
        private String description;

        @NotNull(message = "Поле price является обязательным!")
        @Min(value = 1, message = "Минимальное значение 1")
        private BigDecimal price;

        @NotNull(message = "Поле quantity является обязательным!")
        @Min(value = 1, message = "Минимальное значение 1")
        private Integer quantity;

        private Long categoryId;

        private Long brandId;

        private Map<String, Object> attributes;
}
