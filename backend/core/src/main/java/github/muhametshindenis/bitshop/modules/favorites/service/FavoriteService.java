package github.muhametshindenis.bitshop.modules.favorites.service;

import github.muhametshindenis.bitshop.modules.favorites.dto.CreateFavoriteDto;
import github.muhametshindenis.bitshop.modules.favorites.dto.FavoriteResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19.09.2025 September 2025
 */
public interface FavoriteService {
    FavoriteResponseDto create(CreateFavoriteDto createFavoriteDto, UserDetails userDetails);
    List<FavoriteResponseDto> findAll(UserDetails userDetails);
    FavoriteResponseDto findById(Long productId, UserDetails userDetails);
    void deleteById(Long productId, UserDetails userDetails);
    void deleteAll(UserDetails userDetails);
}
