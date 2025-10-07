package github.muhametshindenis.bitshop.modules.reviews.service;

import github.muhametshindenis.bitshop.modules.reviews.dto.CreateReviewDto;
import github.muhametshindenis.bitshop.modules.reviews.dto.ReviewResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 07 October 2025
 */
public interface ReviewService {
    ReviewResponseDto create(UserDetails userDetails, CreateReviewDto createReviewDto);
}
