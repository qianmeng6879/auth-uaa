package top.mxzero.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.io.PrintWriter;

/**
 * @author Zero
 * @email qianmeng6879@163.com
 * @since 2023/3/4
 */
@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain authorizationClientSecurityFilterChain(HttpSecurity http) throws Exception {
        SecurityFilterChain build = http.authorizeHttpRequests()
                .requestMatchers("/authorized/**").permitAll()
                .requestMatchers("/user/**").authenticated()

                .anyRequest().permitAll()
                .and().oauth2ResourceServer().jwt().and().authenticationEntryPoint((request, response, authException) -> {
                    PrintWriter writer = response.getWriter();
                    writer.print(authException.getMessage());
                    writer.close();
                }).and().oauth2Client().and().oauth2Login().and().logout().and().build();
        return build;
    }

}
