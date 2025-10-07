package github.muhametshindenis.bitshop.modules.reviews.dto;

import github.muhametshindenis.bitshop.modules.users.dto.UserResponseDto;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 07 October 2025
 */
public record ReviewResponseDto(
    Long id,
    String comment,
    Double rating,
    UserResponseDto user
) {
}
