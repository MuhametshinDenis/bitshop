package github.muhametshindenis.bitshop.modules.users.service.impl;

import github.muhametshindenis.bitshop.common.exception.ConflictException;
import github.muhametshindenis.bitshop.common.exception.NotFoundException;
import github.muhametshindenis.bitshop.modules.storage.service.StorageService;
import github.muhametshindenis.bitshop.modules.users.dto.CreateUserDto;
import github.muhametshindenis.bitshop.modules.users.dto.UpdateUserDto;
import github.muhametshindenis.bitshop.modules.users.dto.UserResponseDto;
import github.muhametshindenis.bitshop.modules.users.entity.User;
import github.muhametshindenis.bitshop.modules.users.mapper.UserMapper;
import github.muhametshindenis.bitshop.modules.users.repository.UserRepository;
import github.muhametshindenis.bitshop.modules.users.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 27.08.2025 August 2025
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final StorageService storageService;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, StorageService storageService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.storageService = storageService;
    }

    @Override
    public void create(CreateUserDto createUserDto) {
        if (this.userRepository.findByEmail(createUserDto.getEmail()).isPresent()) {
            throw new ConflictException("Пользователь с email: " + createUserDto.getEmail() + " уже существует!");
        }
        createUserDto.setPassword(bCryptPasswordEncoder.encode(createUserDto.getPassword()));
        userRepository.save(UserMapper.toEntity(createUserDto));
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public Optional<UserResponseDto> findByEmail(String email) {
        return userRepository.findByEmail(email).map(UserMapper::toResponse);
    }

    @Override
    public Optional<UserResponseDto> findById(Long id) {
        return userRepository.findById(id).map(UserMapper::toResponse);
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Пользователь с ID: " + id + " не найден"));

        userRepository.delete(user);
    }

    @Override
    public boolean validateUserCredentials(String email, String password) {
        return this.userRepository.findByEmail(email)
                .filter(value -> this.bCryptPasswordEncoder.matches(password, value.getPassword()))
                .isPresent();
    }

    /**
     * Обновляет данные пользователя на основе информации из {@link UpdateUserDto}.
     * <p>
     * Все поля DTO, кроме аватара, обновляются через {@link UserMapper#updateFromDto(UpdateUserDto, User)}.
     * Аватар обрабатывается отдельно через {@link github.muhametshindenis.bitshop.modules.storage.service.StorageService}.
     * <p>
     * Если пользователь с указанным email не найден, выбрасывается {@link NotFoundException}.
     *
     * @param updateUserDto DTO с данными для обновления пользователя
     * @param userDetails   текущий аутентифицированный пользователь
     * @return {@link UserResponseDto} с обновлёнными данными пользователя
     * @throws IOException если возникает ошибка при сохранении аватара через StorageService
     */
    @Override
    public UserResponseDto update(UpdateUserDto updateUserDto, UserDetails userDetails) throws IOException {
        String userEmail = userDetails.getUsername();

        User user = this.userRepository.findByEmail(userEmail).orElseThrow(() ->
                new NotFoundException("Пользователь с email: " + userEmail + " не найден!"));

        UserMapper.updateFromDto(updateUserDto, user);

        if (updateUserDto.avatar() != null) {
            String presignUrl = this.storageService.save("/avatars", updateUserDto.avatar());
            user.setAvatarUrl(presignUrl);
        }

        return UserMapper.toResponse(this.userRepository.save(user));
    }
}
