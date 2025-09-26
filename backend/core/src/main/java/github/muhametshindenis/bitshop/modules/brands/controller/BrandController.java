package github.muhametshindenis.bitshop.modules.brands.controller;

import github.muhametshindenis.bitshop.common.dto.ApiResponse;
import github.muhametshindenis.bitshop.modules.brands.dto.CreateBrandDto;
import github.muhametshindenis.bitshop.modules.brands.dto.UpdateBrandDto;
import github.muhametshindenis.bitshop.modules.brands.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestPart(value = "brand") CreateBrandDto createBrandDto, @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.brandService.create(createBrandDto, image));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.brandService.findALl());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long brandId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.brandService.findById(brandId));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@RequestPart(value = "brand", required = false)UpdateBrandDto updateBrandDto,
                                    @RequestPart(value = "image", required = false) MultipartFile image,
                                    @PathVariable("id") Long brandId) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(this.brandService.update(updateBrandDto, image, brandId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long brandId) {
        this.brandService.deleteById(brandId);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(
                "Бренд с ID: " + brandId + " успешно удален!"
        ));
    }
}
