package github.muhametshindenis.bitshop.common.config;

import github.muhametshindenis.bitshop.modules.users.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

/**
 * @author Denis Muhametshin
 * @see <a href="https://github.com/MuhametshinDenis">https://github.com/MuhametshinDenis</a>
 * @since 19 August 2025
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Value("${server.servlet.session.cookie.name}")
	private String SESSION_COOKIE_NAME;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth
						//TODO: Изменить requestMatchers, подставив только пути для входа и логина
						.requestMatchers("/**").permitAll()
						.anyRequest().authenticated())
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
						.maximumSessions(1))
				.logout(logout -> logout
						.logoutUrl("/api/v1/auth/logout")
						.logoutSuccessUrl("/api/v1/auth/login")
						.invalidateHttpSession(true)
						.deleteCookies(SESSION_COOKIE_NAME)
						.permitAll())
				.build();
	}
	
	@Bean
	public SecurityContextRepository securityContextRepository() {
		return new HttpSessionSecurityContextRepository();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration
	) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public UserDetailsService userDetailsService(UserServiceImpl userServiceImpl) {
		return email -> userServiceImpl.findUserByEmail(email)
				.map(user -> User
						.builder()
						.username(user.getEmail())
						.password(user.getPassword())
						.roles("DEFAULT")
						.build())
				.orElseThrow(() ->new UsernameNotFoundException("User not found: " + email));
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
