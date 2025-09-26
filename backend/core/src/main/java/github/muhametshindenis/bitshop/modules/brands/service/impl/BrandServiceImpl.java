package github.muhametshindenis.bitshop.modules.brands.service.impl;

import github.muhametshindenis.bitshop.common.exception.BadRequestException;
import github.muhametshindenis.bitshop.common.exception.ConflictException;
import github.muhametshindenis.bitshop.common.exception.NotFoundException;
import github.muhametshindenis.bitshop.modules.brands.dto.UpdateBrandDto;
import github.muhametshindenis.bitshop.modules.brands.repository.BrandRepository;
import github.muhametshindenis.bitshop.modules.brands.dto.BrandResponseDto;
import github.muhametshindenis.bitshop.modules.brands.dto.CreateBrandDto;
import github.muhametshindenis.bitshop.modules.brands.entity.Brand;
import github.muhametshindenis.bitshop.modules.brands.mapper.BrandMapper;
import github.muhametshindenis.bitshop.modules.brands.service.BrandService;
import github.muhametshindenis.bitshop.modules.storage.service.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final StorageService storageService;

    public BrandServiceImpl(BrandRepository brandRepository, StorageService storageService) {
        this.brandRepository = brandRepository;
        this.storageService = storageService;
    }

    @Override
    public BrandResponseDto create(CreateBrandDto createBrandDto, MultipartFile image) throws IOException {
        if (image == null || image.isEmpty()) {
            throw new BadRequestException("Поле image является обязательным!");
        }

        if (this.brandRepository.findByName(createBrandDto.name()).isPresent()) {
            throw new ConflictException("Бренд с названием: " + createBrandDto.name() + " уже существует!");
        }

        Brand brand = BrandMapper.toEntity(createBrandDto);
        brand.setImageUrl(this.saveBrandImage(image));

        return BrandMapper.toResponse(brandRepository.save(brand));
    }

    @Override
    public List<BrandResponseDto> findALl() {
        return this.brandRepository.findAll()
                .stream()
                .map(BrandMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BrandResponseDto findById(Long brandId) {
        Brand brand = this.brandRepository.findById(brandId).orElseThrow(() ->
                new NotFoundException("Бренд с ID: " + brandId + " не найден!"));

        return BrandMapper.toResponse(brand);
    }

    @Override
    public BrandResponseDto update(UpdateBrandDto updateBrandDto, MultipartFile image, Long brandId) throws IOException {
        if (this.isEmptyUpdate(updateBrandDto, image)) {
            throw new BadRequestException("Нет данных для обновления!");
        }

        Brand brand = this.brandRepository.findById(brandId).orElseThrow(() ->
                new NotFoundException("Бренд с ID: " + brandId + " не найден!"));

        Optional.ofNullable(updateBrandDto.name()).ifPresent(brand::setName);

        if (image != null && !image.isEmpty()) {
            this.deleteBrandImage(brand.getImageUrl());
            brand.setImageUrl(this.saveBrandImage(image));
        }

        return BrandMapper.toResponse(brandRepository.save(brand));
    }

    @Override
    public void deleteById(Long brandId) {
        Brand brand = this.brandRepository.findById(brandId).orElseThrow(() ->
                new NotFoundException("Бренд с ID: " + brandId + " не найден!"));

        this.deleteBrandImage(brand.getImageUrl());

        this.brandRepository.delete(brand);
    }

    @Override
    public Brand findBrandById(Long brandId) {
        return this.brandRepository.findById(brandId).orElseThrow(() ->
                new NotFoundException("Бренд с ID: " + brandId + " не найден!"));
    }

    private boolean isEmptyUpdate(UpdateBrandDto dto, MultipartFile image) {
        return (dto == null && (image == null || image.isEmpty()));
    }

    private void deleteBrandImage(String imageUrl) {
        this.storageService.delete(imageUrl);
    }

    private String saveBrandImage(MultipartFile image) throws IOException {
        return this.storageService.save("brands/", image);
    }
}
