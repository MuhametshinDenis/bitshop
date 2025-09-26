package github.muhametshindenis.bitshop.modules.favorites.mapper;

import github.muhametshindenis.bitshop.modules.favorites.dto.FavoriteResponseDto;
import github.muhametshindenis.bitshop.modules.favorites.entity.Favorite;
import github.muhametshindenis.bitshop.modules.products.entity.Product;
import github.muhametshindenis.bitshop.modules.users.entity.User;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
public class FavoriteMapper {
    public static Favorite toEntity(User user, Product product) {
        return Favorite.builder()
                .user(user)
                .product(product)
                .build();
    }

    public static FavoriteResponseDto toResponse(Favorite favorite) {
        return new FavoriteResponseDto(
                favorite.getId(),
                favorite.getProduct().getId(),
                favorite.getProduct().getName(),
                favorite.getProduct().getImageUrl(),
                favorite.getProduct().getPrice()
        );
    }
}
