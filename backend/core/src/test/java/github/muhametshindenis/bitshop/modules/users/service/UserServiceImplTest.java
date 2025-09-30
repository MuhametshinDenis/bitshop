package github.muhametshindenis.bitshop.modules.users.service;

import github.muhametshindenis.bitshop.common.exception.ConflictException;
import github.muhametshindenis.bitshop.common.exception.NotFoundException;
import github.muhametshindenis.bitshop.modules.storage.service.StorageService;
import github.muhametshindenis.bitshop.modules.users.dto.CreateUserDto;
import github.muhametshindenis.bitshop.modules.users.entity.User;
import github.muhametshindenis.bitshop.modules.users.repository.UserRepository;
import github.muhametshindenis.bitshop.modules.users.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 10.09.2025 September 2025
 */
@DisplayName("Unit тесты сервиса UserService")
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private StorageService storageService;

    private User user;

    @BeforeEach
    void setUp() {
        this.user = User.builder()
                .id(1L)
                .email("example@example.com")
                .password("password")
                .firstName("John")
                .lastName("Doe")
                .build();
    }

    @Test
    void create_WhenEmailNotExists_ShouldSaveUser() {
        CreateUserDto createUserDto = CreateUserDto.builder()
                .email("example@example.com")
                .password("password")
                .firstName("John")
                .lastName("Doe")
                .build();

        // given
        when(userRepository.findByEmail(createUserDto.getEmail())).thenReturn(Optional.empty());
        when(bCryptPasswordEncoder.encode(createUserDto.getPassword())).thenReturn("hashed");

        // when
        this.userServiceImpl.create(createUserDto);

        // then
        verify(userRepository).save(any(User.class));
        verify(bCryptPasswordEncoder).encode(anyString());
        verify(userRepository).findByEmail(createUserDto.getEmail());
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void create_WhenEmailExists_ShouldConflictException() {
        // given
        CreateUserDto createUserDto = CreateUserDto.builder()
                .email("example@example.com")
                .password("password")
                .firstName("John")
                .lastName("Doe")
                .build();
        when(userRepository.findByEmail(createUserDto.getEmail())).thenReturn(Optional.of(user));

        // when + then
        assertThrows(ConflictException.class, () -> userServiceImpl.create(createUserDto));
        verify(userRepository).findByEmail(createUserDto.getEmail());
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void findUserByEmail_WhenUserExists_ShouldReturnOptionalUser() {
        // given
        when(userRepository.findByEmail("example@example.com")).thenReturn(Optional.of(user));

        // when
        Optional<User> foundUser = userServiceImpl.findUserByEmail("example@example.com");

        // then
        assertTrue(foundUser.isPresent());
        assertEquals(user.getEmail(), foundUser.get().getEmail());
        assertEquals(user.getFirstName(), foundUser.get().getFirstName());
        assertEquals(user.getLastName(), foundUser.get().getLastName());
        verify(userRepository).findByEmail("example@example.com");
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void findUserByEmail_WhenUserDoesNotExist_ShouldReturnEmptyOptional() {
        // given
        when(userRepository.findByEmail("missing@example.com")).thenReturn(Optional.empty());

        // when
        Optional<User> result = userServiceImpl.findUserByEmail("missing@example.com");

        // then
        assertTrue(result.isEmpty());
        verify(userRepository).findByEmail("missing@example.com");
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void findById_WhenUserExists_ShouldReturnUserResponseDto() {
        // given
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        // when
        var result = userServiceImpl.findByEmail(user.getEmail());

        // then
        assertTrue(result.isPresent());
        assertEquals(user.getEmail(), result.get().email());
        assertEquals(user.getFirstName(), result.get().firstName());
        verify(userRepository).findByEmail(user.getEmail());
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void findById_WhenUserNotExists_ShouldReturnEmptyOptional() {
        // given
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());

        // when
        var result = userServiceImpl.findByEmail(user.getEmail());

        // then
        assertTrue(result.isEmpty());
        verify(userRepository).findByEmail(user.getEmail());
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void deleteById_WhenUserExists_ShouldDelete() {
        // given
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // when
        userServiceImpl.deleteById(1L);

        // then
        verify(userRepository).findById(1L);
        verify(userRepository).delete(user);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void deleteById_WhenUserNotExists_ShouldThrowNotFound() {
        // given
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // when + then
        assertThrows(NotFoundException.class, () -> userServiceImpl.deleteById(1L));
        verify(userRepository).findById(1L);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void validateUserCredentials_WhenPasswordMatches_ShouldReturnTrue() {
        // given
        user.setPassword("hashedPassword");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.matches("password", "hashedPassword")).thenReturn(true);

        // when
        boolean result = userServiceImpl.validateUserCredentials(user.getEmail(), "password");

        // then
        assertTrue(result);
        verify(userRepository).findByEmail(user.getEmail());
        verify(bCryptPasswordEncoder).matches("password", "hashedPassword");
        verifyNoMoreInteractions(userRepository, bCryptPasswordEncoder);
    }

    @Test
    void validateUserCredentials_WhenPasswordDoesNotMatch_ShouldReturnFalse() {
        // given
        user.setPassword("hashedPassword");
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.matches("wrongpass", "hashedPassword")).thenReturn(false);

        // when
        boolean result = userServiceImpl.validateUserCredentials(user.getEmail(), "wrongpass");

        // then
        assertFalse(result);
        verify(userRepository).findByEmail(user.getEmail());
        verify(bCryptPasswordEncoder).matches("wrongpass", "hashedPassword");
        verifyNoMoreInteractions(userRepository, bCryptPasswordEncoder);
    }

    @Test
    void validateUserCredentials_WhenUserNotFound_ShouldReturnFalse() {
        // given
        when(userRepository.findByEmail("missing@mail.com")).thenReturn(Optional.empty());

        // when
        boolean result = userServiceImpl.validateUserCredentials("missing@mail.com", "any");

        // then
        assertFalse(result);
        verify(userRepository).findByEmail("missing@mail.com");
        verifyNoMoreInteractions(userRepository, bCryptPasswordEncoder);
    }
}