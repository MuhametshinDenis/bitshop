package github.muhametshindenis.bitshop.modules.users.service;

import github.muhametshindenis.bitshop.modules.users.entity.User;
import github.muhametshindenis.bitshop.modules.users.repository.UserRepository;
import github.muhametshindenis.bitshop.modules.users.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
}