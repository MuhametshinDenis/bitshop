package github.muhametshindenis.bitshop.modules.users.service;

import github.muhametshindenis.bitshop.common.exception.ConflictException;
import github.muhametshindenis.bitshop.common.exception.NotFoundException;
import github.muhametshindenis.bitshop.modules.users.dto.CreateUserDto;
import github.muhametshindenis.bitshop.modules.users.dto.UpdateUserDto;
import github.muhametshindenis.bitshop.modules.users.dto.UserResponseDto;
import github.muhametshindenis.bitshop.modules.users.entity.User;
import github.muhametshindenis.bitshop.modules.users.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 12.09.2025 September 2025
 */
public interface UserService {
    /**
     * Создаёт нового пользователя на основе переданного {@link CreateUserDto}.
     * <p>
     * Если пользователь с таким email уже существует, выбрасывается {@link ConflictException}.
     * Пароль пользователя шифруется с помощью {@link BCryptPasswordEncoder} перед сохранением.
     *
     * @param createUserDto DTO с данными для создания пользователя
     * @throws ConflictException если пользователь с указанным email уже существует
     */
    void create(CreateUserDto createUserDto);

    /**
     * Находит пользователя по email и возвращает сущность {@link User}.
     *
     * @param email email пользователя для поиска
     * @return {@link Optional} с пользователем, если найден, иначе пустой Optional
     */
    Optional<User> findUserByEmail(String email);

    /**
     * Находит пользователя по email и возвращает DTO {@link UserResponseDto}.
     *
     * @param email email пользователя для поиска
     * @return {@link Optional} с DTO пользователя, если найден, иначе пустой Optional
     */
    Optional<UserResponseDto> findByEmail(String email);

    /**
     * Находит пользователя по ID и возвращает DTO {@link UserResponseDto}.
     *
     * @param id ID пользователя для поиска
     * @return {@link Optional} с DTO пользователя, если найден, иначе пустой Optional
     */
    Optional<UserResponseDto> findById(Long id);

    /**
     * Удаляет пользователя по ID.
     * <p>
     * Если пользователь с указанным ID не найден, выбрасывается {@link NotFoundException}.
     *
     * @param id ID пользователя для удаления
     * @throws NotFoundException если пользователь с указанным ID не найден
     */
    void deleteById(Long id);

    /**
     * Проверяет корректность email и пароля пользователя.
     *
     * @param email    email пользователя
     * @param password пароль пользователя
     * @return true, если пользователь найден и пароль совпадает, иначе false
     */
    boolean validateUserCredentials(String email, String password);

    /**
     * Обновляет данные пользователя на основе информации из {@link UpdateUserDto}.
     * <p>
     * Все поля DTO, кроме аватара, обновляются через {@link UserMapper#updateFromDto(UpdateUserDto, User)}.
     * Аватар обрабатывается отдельно через {@link github.muhametshindenis.bitshop.modules.storage.service.StorageService}.
     * <p>
     * Если пользователь с указанным email не найден, выбрасывается {@link NotFoundException}.
     * <p>
     *
     * @param updateUserDto DTO с данными для обновления пользователя
     * @param userDetails   текущий аутентифицированный пользователь
     * @return {@link UserResponseDto} с обновлёнными данными пользователя
     * @throws IOException если возникает ошибка при сохранении аватара через StorageService
     */
    UserResponseDto update(UpdateUserDto updateUserDto, UserDetails userDetails) throws IOException;
}
