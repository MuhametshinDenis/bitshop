package github.muhametshindenis.bitshop.modules.products.controller;

import github.muhametshindenis.bitshop.common.dto.ApiResponse;
import github.muhametshindenis.bitshop.modules.products.dto.CreateProductDto;
import github.muhametshindenis.bitshop.modules.products.dto.ProductFilterDto;
import github.muhametshindenis.bitshop.modules.products.dto.UpdateProductDto;
import github.muhametshindenis.bitshop.modules.products.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 15.09.2025 September 2025
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestPart("product") CreateProductDto createProductDto, @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.create(createProductDto, image));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long productId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.findById(productId));
    }

    @GetMapping
    public ResponseEntity<?> findByFilters(@ModelAttribute ProductFilterDto productFilterDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.findByFilters(productFilterDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@RequestPart(value = "product", required = false) UpdateProductDto updateProductDto, @RequestPart(name = "image", required = false) MultipartFile image, @PathVariable(value = "id") Long productId) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.update(updateProductDto, image, productId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long productId) {
        this.productService.deleteById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Продукт c ID: " + productId + " успешно удален"));
    }
}
