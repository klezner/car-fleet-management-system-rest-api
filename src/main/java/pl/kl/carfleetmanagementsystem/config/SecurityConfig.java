package pl.kl.carfleetmanagementsystem.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Profile("NO-SEC")
    WebSecurityConfigurerAdapter noAuth() {
        return new WebSecurityConfigurerAdapter() {

            @Override
            protected void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests().anyRequest().permitAll();
            }
        };
    }

    @Bean
    @Profile("SEC")
    WebSecurityConfigurerAdapter basicAuth() {
        return new WebSecurityConfigurerAdapter() {

            @Override
            protected void configure(HttpSecurity http) throws Exception {
                http.csrf().disable();
                http.headers().frameOptions().disable();
                http.authorizeRequests()
                        .mvcMatchers("/").permitAll()
                        .mvcMatchers("/swagger-ui/**").permitAll()
                        .mvcMatchers("/h2-console/**").hasRole("ADMIN")
                        .mvcMatchers(HttpMethod.GET, "/**").hasAnyRole("USER", "ADMIN")
                        .mvcMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
                        .mvcMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
                        .mvcMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated()
                        .and()
                        .httpBasic()
                        .and()
                        .formLogin()
                        .and()
                        .exceptionHandling()
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                        .and()
                        .logout()
                        .and()
                        .userDetailsService(userDetailsService);
            }
        };
    }
}
