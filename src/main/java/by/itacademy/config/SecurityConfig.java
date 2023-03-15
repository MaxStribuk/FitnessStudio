package by.itacademy.config;

import by.itacademy.web.filter.JwtFilter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtFilter filter;

    public SecurityConfig(JwtFilter filter) {
        this.filter = filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
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
                        (request, response, e) -> response.sendError(
                                HttpServletResponse.SC_UNAUTHORIZED,
                                e.getMessage()
                        )
                )
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

                .anyRequest().authenticated();

        http.addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter.class
        );
    }
}