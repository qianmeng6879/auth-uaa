package top.mxzero.uaa.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import top.mxzero.uaa.service.impl.UserDetailsServiceImpl;

@Slf4j
@Configuration
public class WebSecurityConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        LOGGER.info("hello:{}", encoder.encode("hello"));
        return encoder;
    }

    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http
                .formLogin().loginPage("/sso_login").loginProcessingUrl("/login")
                .permitAll()
                .and().csrf().disable()
                .rememberMe().rememberMeParameter("rememberMe")
                .key("mxzero-key")
                .tokenValiditySeconds(3600 * 24 * 3)
                .rememberMeCookieName("mxzero-cookie-rem")
                .and()
                .logout().deleteCookies("mxzero-cookie-rem", "JSESSIONID").permitAll()
                .and()
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/error/**", "/test", "/static/**", "/register*/**").permitAll()
                        .requestMatchers("/user/**").authenticated()
                        .requestMatchers("/staff/**").fullyAuthenticated()
                        .anyRequest().permitAll()
                )
//                .exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
//                    log.info(authException.getClass().getName());
//                    log.info(authException.getMessage());
//                    log.info(authException.getLocalizedMessage());
//                    request.getRequestDispatcher("/error/403").forward(request, response);
//                }).and()
                .oauth2ResourceServer().jwt();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
}