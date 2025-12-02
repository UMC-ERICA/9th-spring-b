package umc.server.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import umc.server.global.auth.jwt.AuthenticationEntryPointImpl;
import umc.server.global.auth.jwt.JwtAuthFilter;
import umc.server.global.auth.jwt.JwtUtil;
import umc.server.global.auth.service.CustomUserDetailsService;

@EnableWebSecurity //스프링시큐리티 설정 활성화
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    private final String[] allowUris = {
            "/login",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
    };

    @Bean
    // SecurityFilterChain 정의
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // url별 접근 규칙 설정
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated() // 그 외 모든 요청에 대해 인증 요구
                )
                // 폼 기반 로그인
                .formLogin(AbstractHttpConfigurer::disable)
                /*.formLogin(form -> form
                        //.defaultSuccessUrl("/swagger-ui/index.html", true) //alwaysUse:false-원래 가려던 url로 redirect
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )*/
                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable) // csrf 보호 기능 비활성화
                .logout(logout -> logout
                        .logoutUrl("/logout") // POST /logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll() // 로그인하지 않은 사용자는 어차피 세션이 없으니 문제없음
                )
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint()))
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(jwtUtil, customUserDetailsService);
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }
}

