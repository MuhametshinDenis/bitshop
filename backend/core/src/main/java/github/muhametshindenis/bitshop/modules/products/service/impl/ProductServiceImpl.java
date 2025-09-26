package github.muhametshindenis.bitshop.modules.products.service.impl;

import github.muhametshindenis.bitshop.common.exception.BadRequestException;
import github.muhametshindenis.bitshop.common.exception.ConflictException;
import github.muhametshindenis.bitshop.common.exception.NotFoundException;
import github.muhametshindenis.bitshop.modules.brands.entity.Brand;
import github.muhametshindenis.bitshop.modules.brands.service.BrandService;
import github.muhametshindenis.bitshop.modules.categories.entity.Category;
import github.muhametshindenis.bitshop.modules.categories.service.CategoryService;
import github.muhametshindenis.bitshop.modules.products.dto.CreateProductDto;
import github.muhametshindenis.bitshop.modules.products.dto.ProductFilterDto;
import github.muhametshindenis.bitshop.modules.products.dto.ProductResponseDto;
import github.muhametshindenis.bitshop.modules.products.dto.UpdateProductDto;
import github.muhametshindenis.bitshop.modules.products.entity.Product;
import github.muhametshindenis.bitshop.modules.products.mapper.ProductMapper;
import github.muhametshindenis.bitshop.modules.products.repository.ProductRepository;
import github.muhametshindenis.bitshop.modules.products.service.ProductService;
import github.muhametshindenis.bitshop.modules.products.spec.ProductSpecs;
import github.muhametshindenis.bitshop.modules.storage.service.StorageService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 15.09.2025 September 2025
 */
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final StorageService storageService;
    private final CategoryService categoryService;
    private final BrandService brandService;

    public ProductServiceImpl(ProductRepository productRepository, StorageService storageService, CategoryService categoryService, BrandService brandService) {
        this.productRepository = productRepository;
        this.storageService = storageService;
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

    @Override
    public ProductResponseDto create(CreateProductDto createProductDto, MultipartFile image) throws IOException {
        if (this.productRepository.findByName(createProductDto.getName()).isPresent()) {
            throw new ConflictException("Продукт с именем: " + createProductDto.getName() + " уже существует!");
        }

        if (image == null || image.isEmpty()) {
            throw new BadRequestException("Поле image является обязательным!");
        }

        Product product = ProductMapper.toEntity(createProductDto);

        if (createProductDto.getCategoryId() != null) {
            Category category = this.categoryService.findCategoryById(createProductDto.getCategoryId());
            product.setCategory(category);
        }

        if (createProductDto.getBrandId() != null) {
            Brand brand = this.brandService.findBrandById(createProductDto.getBrandId());
            product.setBrand(brand);
        }

        String presignUrl = this.storageService.save("products/", image);
        product.setImageUrl(presignUrl);

        return ProductMapper.toResponse(this.productRepository.save(product));
    }

    @Override
    public ProductResponseDto findById(Long productId) {
        return ProductMapper.toResponse(this.productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Продукт с ID: " + productId + " не найден!")));
    }

    @Override
    public List<ProductResponseDto> findByFilters(ProductFilterDto productFilterDto) {
        if (productFilterDto == null) {
            return this.productRepository.findAll().stream().map(ProductMapper::toResponse).collect(Collectors.toList());
        }

        Specification<Product> spec = ProductSpecs.hasCategoryId(productFilterDto.categoryId()).and(ProductSpecs.byMaxPrice(productFilterDto.maxPrice())).and(ProductSpecs.byMinPrice(productFilterDto.minPrice()));

        Pageable pageable = PageRequest.of(productFilterDto.page() - 1, productFilterDto.pageSize());

        return this.productRepository.findAll(spec, pageable).stream().map(ProductMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public ProductResponseDto update(UpdateProductDto updateProductDto, MultipartFile image, Long productId) throws IOException {
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Продукт с ID: " + productId + " не найден!"));

        this.updateProductFields(product, updateProductDto);
        this.updateProductImage(product, image);

        return ProductMapper.toResponse(this.productRepository.save(product));
    }

    @Override
    public void deleteById(Long productId) {
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Продукт с ID: " + productId + " не найден!"));

        this.storageService.delete(product.getImageUrl());
        this.productRepository.delete(product);
    }

    @Override
    public Product findProductById(Long productId) {
        return this.productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Продукт с ID: " + productId + " не найден!"));
    }

    private void updateProductFields(Product product, UpdateProductDto updateProductDto) {
        if (updateProductDto == null) return;

        ProductMapper.updateFromDto(updateProductDto, product);

        Optional.ofNullable(updateProductDto.getCategoryId()).map(categoryService::findCategoryById).ifPresent(product::setCategory);
    }

    private void updateProductImage(Product product, MultipartFile image) throws IOException {
        if (image == null || image.isEmpty()) return;

        storageService.delete(product.getImageUrl());

        String presignUrl = storageService.save("products/", image);
        product.setImageUrl(presignUrl);
    }
}
