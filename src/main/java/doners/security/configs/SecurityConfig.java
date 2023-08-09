package doners.security.configs;

import doners.entity.User;
import doners.data.UserRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder  passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null) return user;
            throw new UsernameNotFoundException("User ‘" + username + "’ not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                 .requestMatchers("/design", "/orders").hasRole("USER")
                 .requestMatchers("/", "/**").permitAll()
                 .requestMatchers(HttpMethod.POST, "/admin/**")
                 .access("hasRole('ADMIN')")
                 .and()
            .formLogin()
                 .loginPage("/login")
                 .defaultSuccessUrl("/design")
                 .and()
            .oauth2Login()
                 .loginPage("/login")
                 .and()
            .logout();

        return http.build();
    }

}
