package github.muhametshindenis.bitshop.modules.favorites.service.impl;

import github.muhametshindenis.bitshop.common.exception.ConflictException;
import github.muhametshindenis.bitshop.common.exception.NotFoundException;
import github.muhametshindenis.bitshop.common.exception.UnauthorizedException;
import github.muhametshindenis.bitshop.modules.favorites.dto.CreateFavoriteDto;
import github.muhametshindenis.bitshop.modules.favorites.dto.FavoriteResponseDto;
import github.muhametshindenis.bitshop.modules.favorites.entity.Favorite;
import github.muhametshindenis.bitshop.modules.favorites.mapper.FavoriteMapper;
import github.muhametshindenis.bitshop.modules.favorites.repository.FavoriteRepository;
import github.muhametshindenis.bitshop.modules.favorites.service.FavoriteService;
import github.muhametshindenis.bitshop.modules.products.entity.Product;
import github.muhametshindenis.bitshop.modules.products.service.ProductService;
import github.muhametshindenis.bitshop.modules.users.entity.User;
import github.muhametshindenis.bitshop.modules.users.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
@Service
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserService userService;
    private final ProductService productService;

    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, UserService userService, ProductService productService) {
        this.favoriteRepository = favoriteRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public FavoriteResponseDto create(CreateFavoriteDto createFavoriteDto, UserDetails userDetails) {
        User user = this.getUserFromUserDetails(userDetails);
        Product product = this.productService.findProductById(createFavoriteDto.productId());

        if (this.favoriteRepository.existsByUserIdAndProductId(user.getId(), product.getId())) {
            throw new ConflictException("Продукт с ID: " + product.getId() + " уже в избранном!");
        }

        Favorite favorite = FavoriteMapper.toEntity(user, product);
        return FavoriteMapper.toResponse(this.favoriteRepository.save(favorite));
    }

    @Override
    public List<FavoriteResponseDto> findAll(UserDetails userDetails) {
        User user = this.getUserFromUserDetails(userDetails);

        return this.favoriteRepository.findAllByUserId(user.getId()).stream().map(FavoriteMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public FavoriteResponseDto findById(Long productId, UserDetails userDetails) {
        User user = this.getUserFromUserDetails(userDetails);
        //TODO: Подумать
        Favorite favorite = this.favoriteRepository.findByUserIdAndProductId(user.getId(), productId).orElseThrow(() ->
                new NotFoundException("Продукта с ID: " + productId + " не существует в избранном!"));

        return FavoriteMapper.toResponse(favorite);
    }

    @Override
    public void deleteById(Long productId, UserDetails userDetails) {
        User user = this.getUserFromUserDetails(userDetails);

        Favorite favorite = this.favoriteRepository.findByUserIdAndProductId(user.getId(), productId).orElseThrow(() ->
                new NotFoundException("Продукта с ID: " + productId + " не существует в избранном!"));

        this.favoriteRepository.delete(favorite);
    }

    @Override
    public void deleteAll(UserDetails userDetails) {
        User user = this.getUserFromUserDetails(userDetails);

        this.favoriteRepository.deleteByUserId(user.getId());
    }

    private User getUserFromUserDetails(UserDetails userDetails) {
        String userEmail = userDetails.getUsername();

        if (userEmail == null || userEmail.isEmpty()) {
            throw new UnauthorizedException("Пользователь не авторизирован!");
        }

        return this.userService.findUserByEmail(userEmail).orElseThrow(() -> new NotFoundException("Пользователь с email: " + userEmail + " не найден"));
    }
}
