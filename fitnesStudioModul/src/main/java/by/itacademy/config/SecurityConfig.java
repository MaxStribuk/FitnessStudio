package by.itacademy.config;

import by.itacademy.web.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtFilter filter)
            throws Exception {
        http = http
                .cors()
                .and()
                .csrf()
                .disable();

        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, e) -> response.setStatus(
                                HttpServletResponse.SC_UNAUTHORIZED)
                )
                .accessDeniedHandler((request, response, e) -> response.setStatus(
                        HttpServletResponse.SC_FORBIDDEN
                ))
                .and();

        http.authorizeRequests()
                .antMatchers("/api/v1/users/registration").permitAll()
                .antMatchers("/api/v1/users/verification").permitAll()
                .antMatchers("/api/v1/users/login").permitAll()
                .antMatchers("/api/v1/users/me").authenticated()
                .antMatchers("/api/v1/users/**").hasRole("ADMIN")

                .antMatchers(HttpMethod.POST, "/api/v1/product/").authenticated()
                .antMatchers(HttpMethod.GET, "/api/v1/product/").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/v1/product/**").authenticated()

                .antMatchers(HttpMethod.GET, "/api/v1/recipe/").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/recipe/").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/v1/recipe/**").authenticated()

                .antMatchers(HttpMethod.GET, "/api/v1/audit/**").hasRole("ADMIN")

                .anyRequest().authenticated();

        http.addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter.class
        );
        return http.build();
    }
}