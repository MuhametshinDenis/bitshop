package github.muhametshindenis.bitshop.modules.brands.mapper;

import github.muhametshindenis.bitshop.modules.brands.dto.BrandResponseDto;
import github.muhametshindenis.bitshop.modules.brands.dto.CreateBrandDto;
import github.muhametshindenis.bitshop.modules.brands.entity.Brand;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
public class BrandMapper {
    public static Brand toEntity(CreateBrandDto createBrandDto) {
        return Brand.builder()
                .name(createBrandDto.name())
                .build();
    }

    public static BrandResponseDto toResponse(Brand brand) {
        return new BrandResponseDto(
                brand.getName(),
                brand.getImageUrl()
        );
    }
}
