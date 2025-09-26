package github.muhametshindenis.bitshop.modules.products.service;

import github.muhametshindenis.bitshop.modules.products.dto.CreateProductDto;
import github.muhametshindenis.bitshop.modules.products.dto.ProductFilterDto;
import github.muhametshindenis.bitshop.modules.products.dto.ProductResponseDto;
import github.muhametshindenis.bitshop.modules.products.dto.UpdateProductDto;
import github.muhametshindenis.bitshop.modules.products.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 15.09.2025 September 2025
 */
public interface ProductService {
    ProductResponseDto create(CreateProductDto createProductDto, MultipartFile image) throws IOException;
    ProductResponseDto findById(Long productId);
    List<ProductResponseDto> findByFilters(ProductFilterDto productFilterDto);
    ProductResponseDto update(UpdateProductDto updateProductDto, MultipartFile image, Long productId) throws IOException;
    void deleteById(Long productId);
    Product findProductById(Long productId);
}
