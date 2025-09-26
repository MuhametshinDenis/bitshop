package github.muhametshindenis.bitshop.modules.brands.service;

import github.muhametshindenis.bitshop.modules.brands.dto.BrandResponseDto;
import github.muhametshindenis.bitshop.modules.brands.dto.CreateBrandDto;
import github.muhametshindenis.bitshop.modules.brands.dto.UpdateBrandDto;
import github.muhametshindenis.bitshop.modules.brands.entity.Brand;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
public interface BrandService {
    BrandResponseDto create(CreateBrandDto createBrandDto, MultipartFile image) throws IOException;
    List<BrandResponseDto> findALl();
    BrandResponseDto findById(Long brandId);
    BrandResponseDto update(UpdateBrandDto updateBrandDto, MultipartFile image, Long brandId) throws IOException;
    void deleteById(Long brandId);
    Brand findBrandById(Long brandId);
}
