package com.hedgerock.spirng.spring_in_action.view_taco_app.config;

import com.hedgerock.spirng.spring_in_action.view_taco_app.data.security_repository.UserRepository;
import com.hedgerock.spirng.spring_in_action.view_taco_app.model.security.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository repository) {
        return username -> {
            User user = repository.findByUsername(username);

            if (user != null) {
                return user;
            }

            throw new UsernameNotFoundException("User " + username + " not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        return http
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/design", "/orders/current").hasRole("USER")
                            .requestMatchers("/", "/**").permitAll();
                })
                .formLogin(form -> {
                    form
                            .loginPage("/login")
                            .defaultSuccessUrl("/", false);
                })
                .logout(form -> {
                    form
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/")
                            .deleteCookies("JSESSIONID");
                })
                .csrf(Customizer.withDefaults())
                .build();
    }
}
